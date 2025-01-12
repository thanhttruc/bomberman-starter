package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.SpeedItem.speed;


public class Move {

    public static boolean can_move = true;

    public static boolean can_down(Entity entity) {
        can_move = true;
        int code = checkWall[entity.getX()/32][entity.getY()/32 + 1];

        if (code == Sprite.CODE_ID_WALL || code == Sprite.CODE_ID_BRICK || code == Sprite.CODE_ID_BOOM) {
            can_move = false;
        }
        return can_move;
    }
    public static boolean can_up(Entity entity) {
        can_move = true;
        int code = checkWall[entity.getX()/32][entity.getY()/32 - 1];
        if (code == Sprite.CODE_ID_WALL || code == Sprite.CODE_ID_BRICK || code == Sprite.CODE_ID_BOOM)  {
            can_move = false;
        }
        return can_move;
    }
    public static boolean can_right(Entity entity) {
        can_move = true;
        int code = checkWall[entity.getX()/32 + 1][entity.getY()/32];
        if (code == Sprite.CODE_ID_WALL || code == Sprite.CODE_ID_BRICK || code == Sprite.CODE_ID_BOOM) {
            can_move = false;
        }
        return can_move;
    }
    public static boolean can_left(Entity entity) {
        can_move = true;
        int code = checkWall[entity.getX()/32 - 1][entity.getY()/32];
        if (code == Sprite.CODE_ID_WALL || code == Sprite.CODE_ID_BRICK || code == Sprite.CODE_ID_BOOM)
        {
            can_move = false;
        }
        return can_move;
    }

    public static void down(Animal animal) {
        if (animal.getX() % 32 == 0 && animal.getY() % 32 == 0) {
            if (animal instanceof Bomber && can_down(animal)) {
                animal.setDirection("down");
                animal.setCount(4 / speed);
                checkRun(animal);
            }
            if (animal instanceof Balloom && can_down(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Oneal && can_down(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Kondoria && can_down(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Doll && can_down(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Minvo && can_down(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Pass && can_down(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Ovape && can_down(animal)) {
                animal.setDirection("down");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }
    private static void down_step(Animal animal) {
        // bomber
        if (animal instanceof  Bomber) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.move_down.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.move_down_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.move_down.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.move_down_2.getFxImage());
                animal.setSwap(1);
            }
        }
        // balloom
        if (animal instanceof Balloom) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.balloom_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.balloom_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.balloom_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.balloom_right2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Kondoria
        if (animal instanceof Kondoria) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.kondoria_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.kondoria_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.kondoria_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.kondoria_right2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Doll
        if (animal instanceof Doll) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.doll_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.doll_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.doll_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.doll_right2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Minvo
        if (animal instanceof Minvo) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.minvo_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.minvo_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.minvo_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.minvo_right2.getFxImage());
                animal.setSwap(1);
            }
        }
        //Oneal
        if (animal instanceof Oneal) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.oneal_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.oneal_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.oneal_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.oneal_right2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Ovape
        if (animal instanceof Ovape) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.ovape_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.ovape_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.ovape_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.ovape_right2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Pass
        if (animal instanceof Pass) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.pass_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.pass_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.pass_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.pass_right2.getFxImage());
                animal.setSwap(1);
            }
        }

    }

    public static void up(Animal animal) {
        if (animal.getX() % 32 == 0 && animal.getY() % 32 == 0) {
            if (animal instanceof Bomber && can_up(animal)) {
                animal.setDirection("up");
                animal.setCount(4 / speed);
                checkRun(animal);
            }
            if (animal instanceof Balloom && can_up(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Oneal && can_up(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Kondoria && can_up(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Doll && can_up(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Minvo && can_up(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Pass && can_up(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Ovape && can_up(animal)) {
                animal.setDirection("up");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }
    private static void up_step(Animal animal) {
        if (animal instanceof  Bomber) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.move_up.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.move_up_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.move_up.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.move_up_2.getFxImage());
                animal.setSwap(1);
            }
        }
        // balloom
        if (animal instanceof  Balloom) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.balloom_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.balloom_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.balloom_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.balloom_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Kondoria
        if (animal instanceof Kondoria) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.kondoria_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.kondoria_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.kondoria_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.kondoria_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Doll
        if (animal instanceof Doll) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.doll_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.doll_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.doll_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.doll_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Doll
        if (animal instanceof Minvo) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.minvo_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.minvo_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.minvo_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.minvo_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        //oneal
        if (animal instanceof  Oneal) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.oneal_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.oneal_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.oneal_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.oneal_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Ovape
        if (animal instanceof Ovape) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.ovape_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.ovape_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.ovape_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.ovape_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Pass
        if (animal instanceof Pass) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.pass_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.pass_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.pass_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.pass_left2.getFxImage());
                animal.setSwap(1);
            }
        }
    }

    public static void left(Animal animal) {
        if (animal.getX() % 32 == 0 && animal.getY() % 32 == 0) {
            if (animal instanceof Bomber && can_left(animal)) {
                animal.setDirection("left");
                animal.setCount(4 / speed);
                checkRun(animal);
            }
            if (animal instanceof Balloom && can_left(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Oneal && can_left(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Kondoria && can_left(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Doll && can_left(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Minvo && can_left(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Pass && can_left(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Ovape && can_left(animal)) {
                animal.setDirection("left");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }
    private static void left_step(Animal animal) {
        if (animal instanceof Bomber) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.move_left.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.move_left_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.move_left.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.move_left_2.getFxImage());
                animal.setSwap(1);
            }
        }
        //balloom
        if (animal instanceof  Balloom) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.balloom_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.balloom_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.balloom_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.balloom_right1.getFxImage());
                animal.setSwap(1);
            }
        }
        // Kondoria
        if (animal instanceof Kondoria) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.kondoria_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.kondoria_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.kondoria_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.kondoria_right1.getFxImage());
                animal.setSwap(1);
            }
        }
        // Doll
        if (animal instanceof Doll) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.doll_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.doll_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.doll_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.doll_right1.getFxImage());
                animal.setSwap(1);
            }
        }
        // Minvo
        if (animal instanceof Minvo) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.minvo_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.minvo_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.minvo_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.minvo_right1.getFxImage());
                animal.setSwap(1);
            }
        }
        //oneal
        if (animal instanceof Oneal) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.oneal_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.oneal_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.oneal_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.oneal_right1.getFxImage());
                animal.setSwap(1);
            }
        }
        // Pass
        if (animal instanceof Pass) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.pass_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.pass_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.pass_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.pass_right1.getFxImage());
                animal.setSwap(1);
            }
        }
        // Ovape
        if (animal instanceof Ovape) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.ovape_right1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.ovape_right2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.ovape_right3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.ovape_right1.getFxImage());
                animal.setSwap(1);
            }
        }

    }

    public static void right(Animal animal) {
        if (animal.getX() % 32 == 0 && animal.getY() % 32 == 0) {
            if (animal instanceof Bomber && can_right(animal)) {
                animal.setDirection("right");
                animal.setCount(4 / speed);
                checkRun(animal);
            }
            if (animal instanceof Balloom && can_right(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Oneal && can_right(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Kondoria && can_right(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Doll && can_right(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Minvo && can_right(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Pass && can_right(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
            if (animal instanceof Ovape && can_right(animal)) {
                animal.setDirection("right");
                animal.setCount(8);
                checkRun(animal);
            }
        }
    }
    private static void right_step(Animal animal) {
        if (animal instanceof  Bomber) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.move_right.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.move_right_1.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.move_right.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.move_right_2.getFxImage());
                animal.setSwap(1);
            }
        }
        //balloom
        if (animal instanceof  Balloom) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.balloom_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.balloom_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.balloom_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.balloom_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Kondoria
        if (animal instanceof Kondoria) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.kondoria_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.kondoria_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.kondoria_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.kondoria_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Doll
        if (animal instanceof Doll) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.doll_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.doll_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.doll_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.doll_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Minvo
        if (animal instanceof Minvo) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.minvo_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.minvo_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.minvo_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.minvo_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        //oneal
        if (animal instanceof  Oneal) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.oneal_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.oneal_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.oneal_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.oneal_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Ovape
        if (animal instanceof  Ovape) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.ovape_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.ovape_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.ovape_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.ovape_left2.getFxImage());
                animal.setSwap(1);
            }
        }
        // Pass
        if (animal instanceof Pass) {
            if (animal.getSwap() == 1) {
                animal.setImg(Sprite.pass_left1.getFxImage());
                animal.setSwap(2);
            } else if (animal.getSwap() == 2) {
                animal.setImg(Sprite.pass_left2.getFxImage());
                animal.setSwap(3);
            } else if (animal.getSwap() == 3) {
                animal.setImg(Sprite.pass_left3.getFxImage());
                animal.setSwap(4);
            } else {
                animal.setImg(Sprite.pass_left2.getFxImage());
                animal.setSwap(1);
            }
        }
    }

    private static void setDirection(String direction, Animal animal, int isMove) {
        switch (direction) {
            case "down":
                down_step(animal);
                animal.setY(animal.getY() + isMove);
                break;
            case "up":
                up_step(animal);
                animal.setY(animal.getY() - isMove);
                break;
            case "left":
                left_step(animal);
                animal.setX(animal.getX() - isMove);
                break;
            case "right":
                right_step(animal);
                animal.setX(animal.getX() + isMove);
                break;
        }
    }

    public static void checkRun(Animal animal) {
        if (animal instanceof Bomber && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 8 * speed);
            animal.setCount(animal.getCount() - 1);
        }
        //run entity
        if (animal instanceof Balloom && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 4);
            animal.setCount(animal.getCount() - 1);
        }
        if (animal instanceof Oneal && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 4);
            animal.setCount(animal.getCount() - 1);
        }
        if (animal instanceof Kondoria && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 4);
            animal.setCount(animal.getCount() - 1);
        }
        if (animal instanceof Doll && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 4);
            animal.setCount(animal.getCount() - 1);
        }
        if (animal instanceof Minvo && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 4);
            animal.setCount(animal.getCount() - 1);
        }
        if (animal instanceof Pass && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 4);
            animal.setCount(animal.getCount() - 1);
        }
        if (animal instanceof Ovape && animal.getCount() > 0) {
            setDirection(animal.getDirection(), animal, 4);
            animal.setCount(animal.getCount() - 1);
        }
    }
}
