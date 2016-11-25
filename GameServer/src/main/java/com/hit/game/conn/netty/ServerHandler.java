package com.hit.game.conn.netty;

import java.sql.Connection;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

	private final Logger log = LoggerFactory.getLogger(ServerHandler.class);

	// 连接ID临时代替方案
	public static final AtomicInteger idAuto = new AtomicInteger();
	// 连接创建数量
	public static final AtomicInteger connNum = new AtomicInteger();

	// 当前连接信息
	private Connection conn;
	private RC4 _rc4 = null;

	/**
	 * 通道读取
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		// 记录数据
		byte[] buff = (byte[]) msg;
		// 超度超长返回

	}

	/**
	 * 建立新连接
	 * 
	 * @throws Exception
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		// 当前Channel
		Channel channel = ctx.channel();

	}

	/**
	 * 通道闲置
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		super.channelInactive(ctx);

		if (conn == null)
			return;

	}

	/**
	 * 有异常发生
	 * 
	 * @throws Exception
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// super.exceptionCaught(ctx, cause);

		// 断连的日志就不打印堆栈了
		if (cause.getMessage().contains("Connection reset by peer")
				|| cause.getMessage().contains("远程主机强迫关闭了一个现有的连接")) {

		} else {
			// 输出错误日志
		}
	}

	@Override
	public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
		super.channelWritabilityChanged(ctx);
		log.trace("发生channelWritabilityChanged事件。");
	}

}
