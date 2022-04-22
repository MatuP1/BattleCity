package com.example.battlecity;

import android.content.Context;

import com.example.battlecity.gameobject.terrain.Brick;
import com.example.battlecity.gameobject.terrain.Iron;
import com.example.battlecity.gameobject.terrain.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapBuilder {
    private static Context context;
    private static Game game;
    public MapBuilder(Context context, Game game){

    }

    public static World createWorld(Context context, Game game){
        World world = null;

        MapBuilder.context = context;
        MapBuilder.game = game;
        readFile(R.raw.lvl_1);
        return world;
    }

    private static void readFile(int lvl_1)  {
        InputStream mapa = context.getResources().openRawResource(lvl_1);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mapa));
        int x = 0,y = 0;
        try{
            String linea = bufferedReader.readLine();
            while(linea!=null){
               x=96; //offset del mapa
                String[] fila = linea.split("");
                for(String s:fila){
                    createTerrain(Integer.parseInt(s),x*5,y*5);
                    x+=10;
                }
                y+=10;
                linea = bufferedReader.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void createTerrain(int type, int x,int y) {
        switch (type){
            case 0:
                break;
            case 1:
                game.addTerrain(new Brick(context,x,y,30));
                break;
            case 2:
                game.addTerrain(new Iron(context,x,y,30));
                break;

        }
    }
}
