package com.hit.game.conn.netty;

public class RC4 {

	private byte[] S = new byte[256];

	public RC4(byte[] key) {
		int keylen = key.length;
		byte[] T = new byte[256];
		for (int jj = 0; jj < 256; jj++) {
			S[jj] = (byte) jj;
			T[jj] = key[jj % keylen];
		}

		int j = 0;
		for (int jj = 0; jj < 256; jj++) {
			j = ((j + S[jj] + T[jj]) % 256) & 0xFF;
			byte t = S[jj];
			S[jj] = S[j];
			S[j] = t;
		}
	}

	private int _i = 0, _j = 0;

	public void crypt(byte[] pt, int start, int end) {
		byte[] s = S;
		if (end < 0)
			end = pt.length;
		for (int i = start; i < end; i++) {
			_i = ((_i + 1) % 256) & 0xFF;
			_j = ((_j + s[_i]) % 256) & 0xFF;
			// classic swap
			byte temp = s[_i];
			s[_i] = s[_j];
			s[_j] = temp;
			int t = ((s[_i] + s[_j]) % 256) & 0xFF;
			byte k = s[t];
			pt[i] = (byte) (k ^ pt[i]);
		}
	}

}