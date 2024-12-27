package com.quiz;
 
import java.util.*;

public class QuizGame {
   
    private static final int TOTAL_QUESTIONS = 5; 
    private static final String[] QUESTIONS = {
        "What is the capital of France?",
        "Which planet is known as the Red Planet?",
        "Who wrote 'To Kill a Mockingbird'?",
        "What is the largest ocean on Earth?",
        "In which year did the Titanic sink?",
        
    };

    private static final String[][] OPTIONS = {
        {"A) Berlin", "B) Madrid", "C) Paris", "D) Rome"},
        {"A) Earth", "B) Mars", "C) Jupiter", "D) Saturn"},
        {"A) Harper Lee", "B) Mark Twain", "C) J.K. Rowling", "D) Ernest Hemingway"},
        {"A) Atlantic Ocean", "B) Indian Ocean", "C) Arctic Ocean", "D) Pacific Ocean"},
        {"A) 1905", "B) 1912", "C) 1918", "D) 1923"}
    };

    private static final char[] ANSWERS = {'C', 'B', 'A', 'D', 'B' };
    private static final long[] PRIZE_MONEY = {1000, 2000, 3000, 5000, 10000};

    private static boolean lifeline5050Used = false;
    private static boolean lifelineAskAudienceUsed = false;
    private static boolean lifelinePhoneFriendUsed = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long prize = 0;
        int currentQuestionIndex = 0;

        while (currentQuestionIndex < TOTAL_QUESTIONS) {
            System.out.println("\nQuestion " + (currentQuestionIndex + 1) + ": " + QUESTIONS[currentQuestionIndex]);
            for (String option : OPTIONS[currentQuestionIndex]) {
                System.out.println(option);
            }
            System.out.println("Please choose an option (A, B, C, D), or use a lifeline:");
            System.out.println("Available Lifelines:");
            if (!lifeline5050Used) System.out.println("Type '50:50' to use the 50:50 lifeline");
            if (!lifelineAskAudienceUsed) System.out.println("Type 'Ask Audience' to use the Ask Audience lifeline");
            if (!lifelinePhoneFriendUsed) System.out.println("Type 'Phone Friend' to use the Phone Friend lifeline");

            String input = scanner.nextLine().toUpperCase();

            if (input.equals("50:50") && !lifeline5050Used) {
                use5050(scanner, currentQuestionIndex);
                lifeline5050Used = true;
                continue;
            } else if (input.equals("ASK AUDIENCE") && !lifelineAskAudienceUsed) {
                askAudience();
                lifelineAskAudienceUsed = true;
                continue;
            } else if (input.equals("PHONE FRIEND") && !lifelinePhoneFriendUsed) {
                phoneFriend();
                lifelinePhoneFriendUsed = true;
                continue;
            }

            if (input.length() == 1 && (input.charAt(0) >= 'A' && input.charAt(0) <= 'D')) {
                if (input.charAt(0) == ANSWERS[currentQuestionIndex]) {
                    prize = PRIZE_MONEY[currentQuestionIndex];
                    System.out.println("Correct! You have won: " + prize);
                    currentQuestionIndex++;
                } else {
                    System.out.println("Wrong answer! Game over. You win: " + prize);
                    break;
                }
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }

       
        System.out.println("\nGame Over!");
        System.out.println("Your final prize money is: " + prize);
        generateFinalReport(prize);
        scanner.close();
    }

    private static void use5050(Scanner scanner, int questionIndex) {
        System.out.println("Using 50:50 lifeline...");
        List<Character> options = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D'));
        options.remove(Character.valueOf(ANSWERS[questionIndex]));
        Collections.shuffle(options);
        options = options.subList(0, 2); 

        System.out.println("The remaining options are:");
        for (char option : options) {
            System.out.println(OPTIONS[questionIndex][option - 'A']);
        }
    }

    private static void askAudience() {
        System.out.println("Asking the audience...");
        Random rand = new Random();
        int percentage = rand.nextInt(51) + 25;  
        System.out.println("The audience thinks the correct answer is option " + ANSWERS[0] + " with approximately " + percentage + "% certainty.");
    }

    private static void phoneFriend() {
        System.out.println("Phoning a friend...");
        Random rand = new Random();
        String[] hints = {
            "I'm not sure, but I think it's option A",
            "It could be option B",
            "I believe it's option C",
            "I'm guessing it's option D"
        };
        System.out.println("Friend's hint: " + hints[rand.nextInt(hints.length)]);
    }

    private static void generateFinalReport(long prize) {
        System.out.println("\n--- Final Report ---");
        System.out.println("Total Questions: " + TOTAL_QUESTIONS);
        System.out.println("Final Prize Money: " + prize);
        System.out.println("Lifelines Used: " + getLifelinesUsed());
    }

    private static String getLifelinesUsed() {
        StringBuilder lifelines = new StringBuilder();
        if (lifeline5050Used) lifelines.append("50:50 ");
        if (lifelineAskAudienceUsed) lifelines.append("Ask Audience ");
        if (lifelinePhoneFriendUsed) lifelines.append("Phone Friend ");
        return lifelines.toString().trim();
    }
}
