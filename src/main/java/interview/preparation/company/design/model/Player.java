package interview.preparation.company.design.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Player {
    public int id;
    public long score;
    public String name;

    public Player(int id,long score)
    {
        this.id = id;
        this.score = score;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id && score == player.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score);
    }

    public void setName(String name) {
        this.name = name;
    }


}
