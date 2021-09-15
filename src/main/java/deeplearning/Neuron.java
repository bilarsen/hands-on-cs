package deeplearning;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Neuron {

    private final int[][] synapses;

    private int boundary;

    public Neuron(int n, int m) {
        this.synapses = new int[n][m];
    }

    public void learn(List<File> files, int cycleCount, int boudnary) {
        this.boundary = boudnary;
        int prevMatches = 0;
        int currentMatches = 0;
        for (int cycle = 0; cycle < cycleCount; cycle++) {
            for (File file : files) {
                boolean correctFile = file.getName().contains("positive");
                int[][] scannedInput = parseScannedInput(file);
                int result = activationFunction(scannedInput);
                if (!correctFile && result == 1) lowerWeights(scannedInput);
                else if (correctFile && result == 0) increaseWeights(scannedInput);
                else currentMatches++;
            }
            if (currentMatches == files.size()) break;
            else if (currentMatches < prevMatches) boudnary--;
            else if (currentMatches > prevMatches) boudnary++;
            prevMatches = currentMatches;
            currentMatches = 0;
        }
    }

    private int activationFunction(int[][] fileData) {
        int sum = 0;
        for (int i = 0; i < synapses.length; i++) {
            for (int j = 0; j < synapses[i].length; j++) {
                sum += fileData[i][j] * synapses[i][j];
            }
        }
        return sum < boundary ? 0 : 1;
    }

    private int[][] parseScannedInput(File file) {
        int[][] dataMatrix = new int[synapses.length][synapses[1].length];
        int rowCounter = 0;
        int[] scannedRow;
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                scannedRow = Arrays.stream(scanner.nextLine().split("")).mapToInt(Integer::parseInt).toArray();
                dataMatrix[rowCounter++] = Arrays.copyOf(scannedRow, scannedRow.length);
            }
        } catch (FileNotFoundException e) {
            return dataMatrix;
        }
        return dataMatrix;
    }

    private void increaseWeights(int[][] fileData) {
        for (int i = 0; i < synapses.length; i++) {
            for (int j = 0; j < synapses[i].length; j++) {
                synapses[i][j] += fileData[i][j];
            }
        }
    }

    private void lowerWeights(int[][] fileData) {
        for (int i = 0; i < synapses.length; i++) {
            for (int j = 0; j < synapses[i].length; j++) {
                synapses[i][j] -= fileData[i][j];
            }
        }
    }
}
