package net.sourceforge.jaad.spi.javasound;

import net.sourceforge.jaad.aac.syntax.BitStream;
import net.sourceforge.jaad.adts.ADTSDemultiplexer;

import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;
import java.io.*;
import java.net.URL;

public class AACAudioFileReader extends AudioFileReader {

	public static final AudioFileFormat.Type AAC = new AudioFileFormat.Type("AAC", "aac");
	public static final AudioFileFormat.Type MP4 = new AudioFileFormat.Type("MP4", "mp4");
	private static final AudioFormat.Encoding AAC_ENCODING = new AudioFormat.Encoding("AAC");

	@Override
	public AudioFileFormat getAudioFileFormat(InputStream in) throws UnsupportedAudioFileException, IOException {
		try {
			if(!in.markSupported()) in = new BufferedInputStream(in);
			in.mark(4);
			return getAudioFileFormat(in, AudioSystem.NOT_SPECIFIED);
		}
		finally {
			in.reset();
		}
	}

	@Override
	public AudioFileFormat getAudioFileFormat(URL url) throws UnsupportedAudioFileException, IOException {
		final InputStream in = url.openStream();
		try {
			return getAudioFileFormat(in);
		}
		finally {
			if(in!=null) in.close();
		}
	}

	@Override
	public AudioFileFormat getAudioFileFormat(File file) throws UnsupportedAudioFileException, IOException {
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			in.mark(1000);
			final AudioFileFormat aff = getAudioFileFormat(in, (int) file.length());
			in.reset();
			return aff;
		}
		finally {
			if(in!=null) in.close();
		}
	}

	private AudioFileFormat getAudioFileFormat(InputStream in, int mediaLength) throws UnsupportedAudioFileException, IOException {
		final byte[] b = new byte[8];
		in.read(b);
		boolean canHandle = false;
		if(new String(b, 4, 4).equals("ftyp")) canHandle = true;
		else {
			final BitStream bit = new BitStream(b);
			try {
				ADTSDemultiplexer adts = new ADTSDemultiplexer(in);
				canHandle = true;
			}
			catch(Exception e) {
				canHandle = false;
			}
		}
		if(canHandle) {
			final AudioFormat format = new AudioFormat(AAC_ENCODING, AudioSystem.NOT_SPECIFIED, AudioSystem.NOT_SPECIFIED, mediaLength, AudioSystem.NOT_SPECIFIED, AudioSystem.NOT_SPECIFIED, true);
			return new AudioFileFormat(AAC, format, AudioSystem.NOT_SPECIFIED);
		}
		else throw new UnsupportedAudioFileException();
	}

	//================================================
	@Override
	public AudioInputStream getAudioInputStream(InputStream in) throws UnsupportedAudioFileException, IOException {
		try {
			if(!in.markSupported()) in = new BufferedInputStream(in);
			in.mark(1000);
			final AudioFileFormat aff = getAudioFileFormat(in, AudioSystem.NOT_SPECIFIED);
			in.reset();
			return new MP4AudioInputStream(in, aff.getFormat(), aff.getFrameLength());
		}
		catch(UnsupportedAudioFileException e) {
			in.reset();
			throw e;
		}
		catch(IOException e) {
			in.reset();
			throw e;
		}
	}

	@Override
	public AudioInputStream getAudioInputStream(URL url) throws UnsupportedAudioFileException, IOException {
		final InputStream in = url.openStream();
		try {
			return getAudioInputStream(in);
		}
		catch(UnsupportedAudioFileException e) {
			if(in!=null) in.close();
			throw e;
		}
		catch(IOException e) {
			if(in!=null) in.close();
			throw e;
		}
	}

	@Override
	public AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException {
		final InputStream in = new FileInputStream(file);
		try {
			return getAudioInputStream(in);
		}
		catch(UnsupportedAudioFileException e) {
			if(in!=null) in.close();
			throw e;
		}
		catch(IOException e) {
			if(in!=null) in.close();
			throw e;
		}
	}
}
