package calebxzhou.rdi.core.sound

import calebxzhou.rdi.core.util.OggPlayer
import calebxzhou.rdi.core.util.ThreadPool
import net.minecraft.util.thread.NamedThreadFactory
import net.sourceforge.jaad.aac.Decoder
import net.sourceforge.jaad.aac.SampleBuffer
import net.sourceforge.jaad.mp4.MP4Container
import net.sourceforge.jaad.mp4.api.AudioTrack
import net.sourceforge.jaad.mp4.api.Frame
import net.sourceforge.jaad.mp4.api.Movie
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.io.RandomAccessFile
import java.util.concurrent.Executors
import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.LineUnavailableException
import javax.sound.sampled.SourceDataLine

object RdiSoundPlayer {
    private val threadPool = Executors.newSingleThreadExecutor(NamedThreadFactory("RdiMusicPlayThread"))
        fun playOgg(musicStream:InputStream) {
            threadPool.submit { OggPlayer(musicStream).start() }
        }

        /*fun playAac(musicFile: File?) {
            // local vars
            var b: ByteArray // array for the actual audio Data during the playback
            val track: AudioTrack // track we are playing atm
            val af: AudioFormat // the track's format
            val line: SourceDataLine // the line we'll use the get our audio to the speaker's
            val dec: Decoder // decoder to get the audio bytes
            var frame: Frame //
            val buf: SampleBuffer //
            var currentTrack: Int // index of current track from playlist
            val cont: MP4Container // container to open the current track with
            val movie: Movie // and get the content from the container
            val paused = false
            try {
                cont = MP4Container(RandomAccessFile(musicFile, "r")) // open titel with random access
                movie = cont.movie // get content from container,
                val content = movie.tracks
                if (content.isEmpty()) {
                    println("music content=empty")
                }
                track = movie.tracks[0] as AudioTrack // grab first track and set the audioformat
                af = AudioFormat(track.sampleRate.toFloat(), track.sampleSize, track.channelCount, true, true)
                line = AudioSystem.getSourceDataLine(af) // get a DataLine from the AudioSystem
                line.open() // open and
                line.start() // start it
                dec = Decoder(track.decoderSpecificInfo)
                buf = SampleBuffer()
                while (track.hasMoreFrames()) // while we have frames left
                {
                    frame = track.readNextFrame() // read next frame,
                    dec.decodeFrame(frame.data, buf) // decode it and put into the buffer
                    b = buf.data // write the frame data from the buffer to our byte-array
                    line.write(b, 0, b.size) // and from there write the byte array into our open AudioSystem DataLine
                    while (paused) // check if we should pause
                    {
                        Thread.sleep(500) // if yes, stay half a second
                        if (Thread.interrupted()) // check if we should stop possibly
                        {
                            line.close() // if yes, close line and
                            return  // exit thread
                        }
                    }
                    if (Thread.interrupted()) // if not in pause, still check on each frame if we should
                    {                               // stop. If so
                        line.close() // close line and
                        return  // exit thread
                    }
                }
                line.close() // after titel is over, close line
            } catch (e: LineUnavailableException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }*/
}
