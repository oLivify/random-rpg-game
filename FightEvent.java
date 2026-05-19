

import java.util.Random;
import java.util.Scanner;

public class FightEvent {

    // An enum is a special datatype that represents a group of constants
    // (unchangeable variables, like final variables).
    public enum Outcome {
        PLAYER_WIN,
        PLAYER_LOSE,
        PLAYER_FLED,
        NO_NPC,
        FRIENDLY_NPC
    }

    private Random rng;
    private Player player;
    private Npc currentNpc;

    public FightEvent(Random rng, Player player, Npc currentNpc) {
        this.rng = rng;
        this.player = player;
        this.currentNpc = currentNpc;
    }

    public FightEvent.Outcome execute() {
        Scanner input = new Scanner(System.in);
        if (currentNpc == null) {
            Main.typewriter(50, "There is nobody here to fight.\n");
            return FightEvent.Outcome.NO_NPC;
        }
        if (currentNpc instanceof Enemy == false) {
            Main.typewriter(50, currentNpc.getName() + " doesn't want to fight you.\n");
            return FightEvent.Outcome.FRIENDLY_NPC;
        }
        Enemy enemy = (Enemy) currentNpc;
        int damageToEnemy = 0;
        int damageToPlayer = 0;
        while (player.getHealth() > 0) {
            Main.typewriter(5, "FIGHT!!! p = punch, k = kick, r = run"
                    + (player.getBackpack().getSize() == 0 ? "" : ", x = use item: "));
            String command = input.next();
            command = command.toLowerCase();
            if (command.equals("r")) {
                int escapeDiceRoll = rng.nextInt(6) + 1;

                if (escapeDiceRoll <= 2) {
                    // 33% escaped without damage
                    Main.typewriter(50, "You escaped successfully\n");
                    return FightEvent.Outcome.PLAYER_FLED;
                }
                if (escapeDiceRoll <= 4) {
                    // 33% escaped with some extra damage
                    Main.typewriter(50, "You escaped... but " + enemy.getName() + " hits you as you run away...\n");
                    damageToPlayer = enemy.attackPlayer(rng, player);
                    player.loseHealth(damageToPlayer);
                    return FightEvent.Outcome.PLAYER_FLED;
                } else {
                    // 33% could not escape
                    Main.typewriter(50, "Oof! Tried to run away, but could not escape!\n");
                }
            }
            damageToEnemy = player.attackEnemy(rng, command, enemy);
            enemy.loseHealth(damageToEnemy);
            player.getBackpack().removeBrokenItems();
            if (enemy.getHealth() > 0) {
                // enemyAttackPlayer
                damageToPlayer = enemy.attackPlayer(rng, player);
                player.loseHealth(damageToPlayer);
            } else {
                Main.typewriter(50, enemy.getName() + " fainted! You won the fight!\n");
                player.increaseEnemiesDefeated();
                return FightEvent.Outcome.PLAYER_WIN;
            }
        }
        if (player.getHealth() <= 0) {
            Main.typewriter(50, "You have been slain by " + enemy.getName() + "\n");
            return FightEvent.Outcome.PLAYER_LOSE;
        }
        input.close();
        return null;

    }
}
