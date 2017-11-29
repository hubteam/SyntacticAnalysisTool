package com.wxw.stream;

import java.io.IOException;

/**
 * 样本刘抽象类
 * @author 王馨苇
 *
 */
public abstract class SampleStream {

	protected PlainTextByTreeStream samples;
	
	protected SampleStream(PlainTextByTreeStream samples) {
		if (samples == null) {
			throw new IllegalArgumentException("samples must not be null!");
		} else {
			this.samples = samples;
		}
	}
	
	/**
	 * 抽象方法
	 * @return
	 * @throws IOException 
	 */
	public abstract String read() throws IOException;

	/**
	 * 重置样本流
	 * @throws IOException
	 * @throws UnsupportedOperationException
	 */
	public void reset() throws IOException, UnsupportedOperationException {
		this.samples.reset();
	}

	/**
	 * 关闭样本流
	 * @throws IOException
	 */
	public void close() throws IOException {
		this.samples.close();
	}
}
