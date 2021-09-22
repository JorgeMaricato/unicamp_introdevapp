package br.com.caipiracreative.android.unicamp_quiz;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Maricato on 22/09/2021.
 */

public class LevelManagement {
    //Retornar um valor pelo texto
    //Retornar um inteiro pelo valor

    private Context context;
    private Resources res;
    private String[] difficulties;

    private Integer chosenDifficulty;
    private Integer nQuestions;

    public LevelManagement(Context new_context){
        this.context = new_context;
        this.res = this.context.getResources();
        this.difficulties = res.getStringArray(R.array.difficulties);
    }

    private void setNQuestions(){
        switch (this.chosenDifficulty){
            case(2):
                this.nQuestions = 7;
                break;
            case(1):
                this.nQuestions = 5;
                break;
            case(0):
            default:
                this.nQuestions = 3;
                break;
        }
    }

    public int getNQuestions(){
        return this.nQuestions;
    }

    public int levelIndex(String level_text){

        for (int i = 0; i < this.difficulties.length; i++) {
            if (level_text.equals(this.difficulties[i])) return i;
        }
        return -1;

    }

    public Boolean setChosenDifficulty(String level_text){
        int level_index = this.levelIndex(level_text);

        if (level_index >= 0){
            this.chosenDifficulty = level_index;
            this.setNQuestions();
            return true;
        }
        else
            return false;
    }
}
