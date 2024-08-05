package proj1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class codingAssigment2 {
    static class NegativeCounterException extends Exception {
        public NegativeCounterException(String message) {
        	System.out.println("Error: " + message);        }
    }

    static class DigitalCounter {
        private int count;
        private String fileName;

        public DigitalCounter(String fileName) {
            this.count = 0;
            this.fileName = fileName;
        }

        public void addPerson() {
            count++;
            writeDataToFile();
        }

        public void removePerson() throws NegativeCounterException {
            if (count <= 0) {
                throw new NegativeCounterException("Count cannot be negative");
            }
            count--;
            writeDataToFile();
        }

        public int getCountPerDay() {
            return count;
        }

        private void writeDataToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
                Date date = new Date();
                writer.write(date.toString() + "," + count);
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DigitalCounter counter = new DigitalCounter("counter_data.csv");

        try {
            counter.addPerson();
            counter.addPerson();
            System.out.println("Count after adding 2 persons: " + counter.getCountPerDay());
            
            counter.removePerson();
            System.out.println("Count after removing 1 person: " + counter.getCountPerDay());
            
            counter.addPerson();
            System.out.println("Count after adding 1 person: " + counter.getCountPerDay());
            
            counter.removePerson();
            System.out.println("Count after removing 1 person: " + counter.getCountPerDay());
            
            counter.removePerson();
            System.out.println("Count after removing 1 person: " + counter.getCountPerDay());
            
            counter.removePerson(); 
        } catch (NegativeCounterException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
