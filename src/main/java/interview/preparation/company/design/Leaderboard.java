package interview.preparation.company.design;

import interview.preparation.company.design.model.Player;

import java.util.*;

public class Leaderboard {

    Map<Integer, Long> map;
    public Leaderboard()
    {
        map = new HashMap<>();
    }

    public void addScore(int playerId, long score)
    {
        map.compute(playerId, (k, v) -> v == null ? score : v + score);
    }

    public long top(int k)
    {
        return map.entrySet().stream().sorted(Map.Entry.<Integer,Long>comparingByValue().reversed())
                .map(Map.Entry::getValue)
                .limit(k)
                .mapToLong(Long::longValue)
                .sum();
    }

    public void reset(int playerId)
    {
        if(map.containsKey(playerId)) {
            map.remove(playerId);
        }
        else throw  new RuntimeException("Player of id"+"playerId not exits");
    }

    static void main() {
        Leaderboard l = new Leaderboard();

        l.addScore(1,100);
        l.addScore(2,200);
        l.addScore(3,300);
        l.addScore(4,400);
        l.addScore(1,500);
        l.addScore(2,600);
        l.addScore(5,300);

        System.out.println(l.top(3));
        l.reset(2);
        System.out.println(l.top(3));
        l.reset(1);
        l.reset(3);
        System.out.println(l.top(3));
    }
}
