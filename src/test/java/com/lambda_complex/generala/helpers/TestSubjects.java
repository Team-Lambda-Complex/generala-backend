package com.lambda_complex.generala.helpers;

import com.lambda_complex.generala.entities.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSubjects {
    public static final Player player1 = new Player(
            1L, // ID generado por la base de datos o mock
            "Juan",
            "hans@hotmail.com",
            "qwerty",
            false, // isRegistered
            null, // registrationDate
            null, // creationDate
            null, // modificationDate
            null, // matchesOwned
            null, // matchesPlayedIn
            null  // plays
    );

    public static final Player player2 = new Player(
            2L, // ID generado por la base de datos o mock
            "Lautaro",
            "cala@hotmail.com",
            "qwerty",
            true, // isRegistered
            null, // registrationDate
            null, // creationDate
            null, // modificationDate
            null, // matchesOwned
            null, // matchesPlayedIn
            null  // plays
    );

    public static final Player player3 = new Player(
            3L, // ID generado por la base de datos o mock
            "Ayrton",
            "gordo@hotmail.com",
            "qwerty",
            false, // isRegistered
            null, // registrationDate
            null, // creationDate
            null, // modificationDate
            null, // matchesOwned
            null, // matchesPlayedIn
            null  // plays
    );

    public List<Player> getAllTestSubjects(){
        return Arrays.asList(player1, player2, player3);
    }
}
