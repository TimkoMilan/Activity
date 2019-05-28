package com.globallogic.activitygame.player;


import java.util.ArrayList;
import java.util.List;

public class PlayerAssigner {

    public List<Player> assignPlayerToGame(List<PlayerCreateDto> playerCreateDtoList) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= playerCreateDtoList.size(); i++) {
            Player player = new Player();
            player.setId(i);
            player.setColor(playerCreateDtoList.get(i - 1).getColor());
            player.setName(playerCreateDtoList.get(i - 1).getName());
            players.add(player);
        }
        return players;
    }
}
