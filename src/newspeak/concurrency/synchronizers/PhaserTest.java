package newspeak.concurrency.synchronizers;

import java.util.concurrent.Phaser;

public class PhaserTest {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1);
        System.out.println(
                phaser.getPhase()
        );

        System.out.println(
                phaser.register()
        );
        System.out.println(
                phaser.arrive()
        );
        System.out.println(
                phaser.arrive()
        );
        System.out.println(phaser.getPhase());

    }
}
