package Transposer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//Program that takes a chord progression (or single chord), and transposes the progression however
//many semitones up or down the user chooses
public class Transposer {
    public static HashMap<String, Integer> scale1 = new HashMap<>();
    public static HashMap<Integer,String> scale2 = new HashMap<>();

    public static void main(String[] args){
        populateMaps();
        System.out.println("How many chords would you like to transpose?");
        Scanner scanner = new Scanner(System.in);
        int numChords = Integer.valueOf(scanner.next());

        //Queue used to store each chord as a String array. First index stores the root note,
        //second index stores whether it is major, minor, or diminished
        Queue<String[]> progression = new LinkedList<>();
        System.out.println("Please type the chords you would like to transpose, separated by a space, with" +
                " 'm' for minor and 'dim' for diminished (ex: C Dm F#dim G)");

        //Populate the Queue
        for(int i=0; i<numChords; i++){
            String chord = scanner.next();
            String[] chordArray = new String[2];
            if(chord.contains("dim")){
                chordArray[0] = chord.substring(0,chord.indexOf("dim"));
                chordArray[1] = "dim";
            }else if(chord.contains("m")){
                chordArray[0] = chord.substring(0,chord.indexOf('m'));
                chordArray[1] = "m";
            }else{
                chordArray[0] = chord;
                chordArray[1] = "";
            }
            progression.add(chordArray);
        }

        //Determine whether to transpose up or down. If transposing down, program alters the number
        //of semitones in order to treat it as if we are transposing up (produces same result)
        System.out.println("Would you like to transpose up or down? (Please type 'up' or 'down')");
        String answer = scanner.next().trim();
        int tones;
        if(answer.equals("up")){
            System.out.println("How many semitones would you like to transpose up?");
            tones = Integer.valueOf(scanner.next());
        }else{
            System.out.println("How many semitones would you like to transpose down?");
            tones = 12-(Integer.valueOf(scanner.next()))%12;
        }

        //Use the HashMaps to transpose each chord, and print them
        System.out.print("Transposed progression: ");
        while(!progression.isEmpty()){
            int newKey = tones + scale1.get(progression.peek()[0]);
            newKey = newKey%12;
            System.out.print(scale2.get(newKey)+progression.peek()[1]+" ");
            progression.remove();
        }
    }
    //Populate the HashMaps used to store each chord and its index
    public static void populateMaps(){
        scale1.put("A", 0);
        scale1.put("A#", 1);
        scale1.put("B", 2);
        scale1.put("C", 3);
        scale1.put("C#", 4);
        scale1.put("D", 5);
        scale1.put("D#", 6);
        scale1.put("E", 7);
        scale1.put("F", 8);
        scale1.put("F#", 9);
        scale1.put("G", 10);
        scale1.put("G#", 11);

        scale2.put(0, "A");
        scale2.put(1, "A#");
        scale2.put(2, "B");
        scale2.put(3, "C");
        scale2.put(4, "C#");
        scale2.put(5, "D");
        scale2.put(6, "D#");
        scale2.put(7, "E");
        scale2.put(8, "F");
        scale2.put(9, "F#");
        scale2.put(10, "G");
        scale2.put(11, "G#");
    }
}
