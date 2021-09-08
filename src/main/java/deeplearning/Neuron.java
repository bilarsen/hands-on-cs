package deeplearning;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Neuron {

    private final int[][] synapses;

    private int boundary;

    public Neuron(int n, int m) {
        this.synapses = new int[n][m];
    }

    public int activationFunction(File file) {
        int sum = 0;
        this.boundary = 35;
        try (Scanner scanner = new Scanner(file)) {
            for (int i = 0; i < synapses.length; i++) {
                for (int j = 0; j < synapses[i].length; j++) {
                    sum += scanner.nextInt() * synapses[i][j];
                }
            }
            return sum < boundary ? 0 : 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void learn(List<File> files, int cycleCount) {
        for (int cycle = 0; cycle < cycleCount; cycle++) {
            for (File file : files) {
                boolean correctFile = file.getName().contains("positive");
                int result = activationFunction(file);
                if (correctFile && !file.getName().startsWith("a")) lowerWeights(file);
                else if (!correctFile && file.getName().startsWith("a")) increaseWeights(file);
            }
        }
    }

    private void increaseWeights(File file) {
    }

    private void lowerWeights(File file) {
    }
}
