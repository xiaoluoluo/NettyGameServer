package com.hit.game.conn.netty;

import com.hit.game.config.Config;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ConnService extends Thread {

	@Override
	public void run() {

		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			// 启动netty监听
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)//
					.channel(NioServerSocketChannel.class)//
					.option(ChannelOption.SO_BACKLOG, 10240)//
					.childOption(ChannelOption.TCP_NODELAY, true)//
					.childOption(ChannelOption.SO_KEEPALIVE, true)//
					.childOption(ChannelOption.WRITE_BUFFER_HIGH_WATER_MARK, 1024 * 1024)// 设置ChannelOutboundBuffer的高水位为256K，防止因客户端网络不好，造成消息阻塞（isWritable为false）消息发送失败
					.childOption(ChannelOption.WRITE_BUFFER_LOW_WATER_MARK, 512 * 1024)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline p = ch.pipeline();
							p.addLast(new Decoder(), new Encoder(), new ServerHandler());
						}
					});
			// 启动
			Channel ch = b.bind(Config.CONN_IP, Config.CONN_PORT).sync().channel();
			ch.closeFuture().sync();
		} catch (Exception e) {

		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}

}
