/* This code manipulates five audio files and combines all of the manipulated
audio samples into one audio sample, hence "AudioCollage".
 */
public class AudioCollage {
    // Returns a new array that rescales a[] by a factor of alpha.
    public static double[] amplify(double[] a, double alpha) {
        int n = a.length;
        double[] amplify = new double[n];
        // Increases volume by factor alpha
        for (int i = 0; i < n; i++) {
            amplify[i] = a[i] * alpha;
        }
        return amplify;
    }


    // Returns a new array that is the reverse of a[].
    public static double[] reverse(double[] a) {
        double[] reverse = new double[a.length];
        // Copies array in reverse order
        for (int i = 0; i < a.length; i++)
            reverse[i] = a[a.length - 1 - i];
        return reverse;
    }

    // Returns a new array that is the concatenation of a[] and b[].
    public static double[] merge(double[] a, double[] b) {
        int n = a.length + b.length;
        // Plays a sample after the other ends
        double[] merge = new double[n];
        for (int i = 0; i < a.length; i++)
            merge[i] = a[i];
        for (int i = a.length; i < n; i++)
            merge[i] = b[i - a.length];
        return merge;
    }

    // Returns a new array that is the sum of a[] and b[],
    // padding the shorter array with trailing 0s if necessary.
    public static double[] mix(double[] a, double[] b) {
        int n = Math.max(a.length, b.length);
        double[] mix = new double[n];
        // a[i] + b[i]
        for (int i = 0; i < a.length; i++)
            mix[i] = a[i];
        for (int i = 0; i < b.length; i++)
            mix[i] += b[i];
        return mix;
    }

    // Returns a new array that changes the speed of a[] by a factor of alpha.
    public static double[] changeSpeed(double[] a, double alpha) {
        // Sets new array length based on alpha
        int n = (int) (a.length / alpha);
        double[] changespeed = new double[n];
        // Copies in the array the sample (i*alpha)
        for (int i = 0; i < n; i++)
            changespeed[i] = a[(int) (i * alpha)];
        return changespeed;
    }

    //
    private static void clamp(double[] a) {
        // Sets values in sample above 1 to 1, and those below -1 to -1
        for (int i = 0; i < a.length; i++) {
            if (a[i] > 1)
                a[i] = 1;
            else if (a[i] < -1)
                a[i] = -1;
        }
    }


    // Creates an audio collage and plays it on standard audio.
    // See below for the requirements.
    public static void main(String[] args) {
        // Reads 5 audio files
        double[] afrobeat = StdAudio.read("afrobeat.wav");
        double[] beatbox = StdAudio.read("beatbox.wav");
        double[] piano = StdAudio.read("piano.wav");
        double[] drum = StdAudio.read("drum.wav");
        double[] clap = StdAudio.read("clap.wav");
        // constant for amplify and change speed
        double ALPHA = 0.25;
        // Audio file manipulation
        double[] test1 = amplify(afrobeat, ALPHA);
        double[] test2 = reverse(beatbox);
        double[] test3 = merge(piano, drum);
        double[] test4 = mix(afrobeat, clap);
        double[] test5 = changeSpeed(afrobeat, ALPHA);
        // Mixing all 5 manipulated audio files into 1
        double[] officialaudio = mix(
                mix(
                        mix(test1, test2),
                        mix(test3, test4)
                ),
                test5
        );
        clamp(officialaudio);
        StdAudio.play(officialaudio);
    }
}
