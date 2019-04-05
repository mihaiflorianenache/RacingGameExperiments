package org.fasttrackit;

import org.fasttrackit.domain.TopWinner;
import org.fasttrackit.service.TopWinnerService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

    private List<Vehicle> competitors = new ArrayList<>();
    private TopWinnerService topWinnerService = new TopWinnerService();
    private Way[] way = new Way[10];
    private int countWay = 0;

    private void addWay() {

        Way wayOne = new Way("Highway", 200);
        Way wayTwo = new Way("Seaside", 100);
        Way wayThree = new Way("Mountain", 300);
        Way wayFour = new Way("City", 10);
        Way wayFive = new Way("County Road", 150);
        Way waySix = new Way("National Way", 250);
        Way waySeven = new Way("European Way", 4000);

        way[0] = wayOne;
        countWay++;

        way[1] = wayTwo;
        countWay++;

        way[2] = wayThree;
        countWay++;

        way[3] = wayFour;
        countWay++;

        way[4] = wayFive;
        countWay++;

        way[5] = waySix;
        countWay++;

        way[6] = waySeven;
        countWay++;
    }

    private void displayWay() {
        int i = 1;
        System.out.println("All ways are");
        for (Way way : way) {
            if (way != null) {
                if (i < countWay) System.out.print(i + "-" + way.getName() + ", ");
                else System.out.println(i + "-" + way.getName() + "\n");
                i++;
            }
        }
    }

    private int getNumberWaySelection() {
        System.out.println("Enter the number of way between 1 and " + countWay);
        try {
            Scanner numberWay = new Scanner(System.in);
            int numberOfWay = numberWay.nextInt();
            if (numberOfWay < 1 || numberOfWay > countWay) {
                return getNumberWaySelection();
            }
            System.out.println("The path chosen by you is " + way[numberOfWay - 1].getName());
            return numberOfWay;
        } catch (InputMismatchException exception) {
            System.out.println("You didn't choose a valid option. Try again.");
            return getNumberWaySelection();
        }
    }

    private String getVehicleNameFromUser() {
        System.out.println("Please enter a vehicle name");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        try {
            checkName(name);
        } catch (MyException exception) {
            System.out.println(exception);
            return getVehicleNameFromUser();
        }
        int i;
        for (i = 0; i < competitors.size(); i++) {
            if (name.equals(competitors.get(i).getName())) {
                System.out.println("In contest is a vehicle with this name. Choose other vehicle.");
                return getVehicleNameFromUser();
            }
        }
        System.out.println("Your vehicle name is: " + name);
        return name;
    }

    private void checkName(String name) throws MyException {
        int i;

        //first two character from car's name must to be letters
        for (i = 0; i < 2; i++) {
            if ((name.charAt(i) < (int) 'a' || name.charAt(i) > (int) 'z') && (name.charAt(i) < (int) 'A' || name.charAt(i) > (int) 'Z'))
                throw new MyException("First two characters must to be letters");
        }

        for (i = 0; i < name.length(); i++) {
            if ((name.charAt(i) < (int) 'a' || name.charAt(i) > (int) 'z') && (name.charAt(i) < (int) 'A' || name.charAt(i) > (int) 'Z') && name.charAt(i) != ' ' && name.charAt(i) != '\t' && name.charAt(i) != '-' && name.charAt(i) != '\'' && name.charAt(i) != '0' && name.charAt(i) != '1' && name.charAt(i) != '2' && name.charAt(i) != '3' && name.charAt(i) != '4' && name.charAt(i) != '5' && name.charAt(i) != '6' && name.charAt(i) != '7' && name.charAt(i) != '8' && name.charAt(i) != '9')
                throw new MyException("Name of vehicle can contains only letters, digits, space, tab, '-' or ''\'");
        }

        //car's name can contains only small letters or only big letters
        for (i = 1; i < name.length(); i++) {
            if ((name.charAt(0) >= (int) 'a' && name.charAt(0) <= (int) 'z' && name.charAt(i) >= (int) 'A' && name.charAt(i) <= (int) 'Z') || (name.charAt(0) >= (int) 'A' && name.charAt(0) <= (int) 'Z' && name.charAt(i) >= (int) 'a' && name.charAt(i) <= (int) 'z'))
                throw new MyException("Name of vehicle can not contains small and big letters. It can contains only small letters or only big letters");
        }
    }

    private double whichIsFuellLevel() {
        System.out.println("Which is the fuell level for vehicle ?");
        Scanner scanner = new Scanner(System.in);
        try {
            double fuellLevel = scanner.nextDouble();
            if (fuellLevel < 1) return whichIsFuellLevel();
            return fuellLevel;
        } catch (InputMismatchException exception) {
            System.out.println("You didn't put a valid value. Try again.");
            return whichIsFuellLevel();
        }
    }

    private void addCompetitors(int competitorCount) {

        int i, j;
        for (i = 0; i < competitorCount; i++) {
            Vehicle vehicleCompetitor = new Vehicle();
            vehicleCompetitor.setName(getVehicleNameFromUser());
            vehicleCompetitor.setFuellLevel(whichIsFuellLevel());
            vehicleCompetitor.setMileage(ThreadLocalRandom.current().nextDouble(5, 15));
            competitors.add(vehicleCompetitor);
        }
    }

    private void displayCompetitors() {
        System.out.print("The competitors are: ");
        int i;
        for (i = 0; i < competitors.size(); i++) {
            if (i != competitors.size() - 1) System.out.print(competitors.get(i).getName() + ", ");
            else System.out.print(competitors.get(i).getName() + "\n");
        }
    }

    private int getCompetitorCountFromUser() {
        System.out.println("Enter the number of competitors");
        Scanner numberCompetitor = new Scanner(System.in);
        try {
            int numberOfCompetitor = numberCompetitor.nextInt();
            if (numberOfCompetitor < 1) return getCompetitorCountFromUser();
            return numberOfCompetitor;
        } catch (InputMismatchException exception) {
            System.out.println("You didn't put a valid number. Try again.");
            return getCompetitorCountFromUser();
        }
    }

    private double getAccelerationSpeedFromUser(Vehicle vehicle,double longWay) {
        System.out.println("Please enter acceleration speed for " + vehicle.getName());
        Scanner scanner = new Scanner(System.in);
        try {
            double accelerationSpeed=scanner.nextDouble();
            if (accelerationSpeed >= longWay) {
                System.out.println("The acceleration speed can not to be bigger or equals than this "+longWay);
                return getAccelerationSpeedFromUser(vehicle, longWay);
            }
            return accelerationSpeed;
        } catch (InputMismatchException exception) {
            System.out.println("You didn't choice a suitable value. Try again.");
            return getAccelerationSpeedFromUser(vehicle,longWay);
        }
    }

    public void start() throws SQLException, IOException, ClassNotFoundException {
        addCompetitors(getCompetitorCountFromUser());
        displayCompetitors();
        addWay();
        displayWay();
        int selectedWayByUser = getNumberWaySelection();
        boolean noWinnerYet = true;
        int competitorsWithoutFuel = 0;

        while (noWinnerYet && competitorsWithoutFuel < competitors.size()) {
            for (Vehicle vehicle : competitors) {
                double variationSpeed = getAccelerationSpeedFromUser(vehicle,way[selectedWayByUser-1].getLength());
                vehicle.accelerate(variationSpeed);
                if (vehicle.getTotalTraveledDistance () >= way[selectedWayByUser-1].getLength ()) {
                    System.out.println("The winner is " + vehicle.getName());

                    TopWinner topWinner = new TopWinner();
                    topWinner.setName(vehicle.getName());
                    topWinner.setWonRaces(1);

                    topWinnerService.createTopWinner(topWinner);

                    noWinnerYet = false;
                    break;
                }
            }
        }
    }
}

