import java.util.Scanner;

class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Unit unit1 = new Unit(in.nextLine());
        Unit unit2 = new Unit(in.nextLine());

        fight(unit1, unit2);
    }

    public static void fight(Unit unit1, Unit unit2) {
        int round = 1;
        while (true) {
            if (unit1.amount == 0) {
                System.out.println(unit2.name + " won! " + unit2.amount + " unit(s) left");
                return;
            }
            System.out.println("Round " + round++);
            int damage = unit1.calculateDamage();
            System.out.println(unit1.amount + " " + unit1.name + "(s) attack(s) " + unit2.amount + " " + unit2.name + "(s) dealing " + damage + " damage");
            System.out.println(unit2.kill(damage) + " unit(s) perish");
            System.out.println("----------");

            if (unit2.amount == 0) {
                System.out.println(unit1.name + " won! " + unit1.amount + " unit(s) left");
                return;
            }
            damage = unit2.calculateDamage();
            System.out.println(unit2.amount + " " + unit2.name + "(s) attack(s) " + unit1.amount + " " + unit1.name + "(s) dealing " + damage + " damage");
            System.out.println(unit1.kill(damage) + " unit(s) perish");
            System.out.println("##########");
        }
    }
}

class Unit {
    String name;
    int amount;
    int health;
    int totalHealth;
    int damage;

    Unit(String data) {
        String[] split = data.split(";");
        name = split[0];
        amount = Integer.parseInt(split[1]);
        health = Integer.parseInt(split[2]);
        totalHealth = health * amount;
        damage = Integer.parseInt(split[3]);
    }

    int calculateDamage() {
        return amount * damage;
    }

    int kill(int damage) {
        totalHealth -= damage;
        int remaining = Math.max((int) Math.ceil((double) totalHealth / health), 0);
        int deaths = amount - remaining;
        amount = remaining;
        return deaths;
    }
}