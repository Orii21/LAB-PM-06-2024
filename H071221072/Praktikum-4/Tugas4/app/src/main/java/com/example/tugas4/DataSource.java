package com.example.tugas4;

import java.util.ArrayList;

public class DataSource {

    public static ArrayList<Instagram> instagrams = generateDummyInstagrams();

    private static ArrayList<Instagram> generateDummyInstagrams() {
        ArrayList<Instagram> instagrams1 = new ArrayList<>();

        instagrams1.add(new Instagram("mpl.id.official","ini caption mpl",R.drawable.mpl,R.drawable.feed_mpl));

        instagrams1.add(new Instagram("alteregoesports","ini caption alterego",R.drawable.alterego,R.drawable.feed_alterego));

        instagrams1.add(new Instagram("onic.esports","ini caption onic",R.drawable.onic,R.drawable.feed_onic));

        instagrams1.add(new Instagram("evosesports","ini caption evos",R.drawable.evos,R.drawable.feed_evos));

        instagrams1.add(new Instagram("teamrrq","ini caption rrq",R.drawable.rrq,R.drawable.feed_rrq));

        instagrams1.add(new Instagram("blacklistintl","ini caption blacklist", R.drawable.blck,R.drawable.feed_blck));

        instagrams1.add(new Instagram("auraesports", "ini caption auraesports",R.drawable.aura,R.drawable.feed_aura));

        instagrams1.add(new Instagram("echophilippines","ini caption echophilippines",R.drawable.echo,R.drawable.feed_echo));

        instagrams1.add(new Instagram("geekfamid","ini caprion geekfamid",R.drawable.geek,R.drawable.feed_geek));

        instagrams1.add(new Instagram("bigetronesports","ini caption bigetron",R.drawable.btr,R.drawable.feed_btr));
        return instagrams1;
    }
}
