package com.nyit.anish.bingohouse;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.datatype.Duration;


public class MainActivity extends ActionBarActivity implements View.OnLongClickListener{

    private static final int MININTEGER=1;
    private static final int MAXINTEGER=75;
    private static final int TOTALRANDOMNOS=15;
    private static final int CARD_ROW_COUNT=5;
    private static final int CARD_COLUMN_COUNT=5;

    int randomNo=0;
    int min=0;
    int max=0;

    Button btn;
    Button mybtn;
    Button bingoBtn;

    int[][] playerArray=new int[][]
            {{R.id.button11,0},
            {R.id.button12,0},
            {R.id.button13,0},
            {R.id.button14,0},
            {R.id.button15,0},
            {R.id.button21,0},
            {R.id.button22,0},
            {R.id.button23,0},
            {R.id.button24,0},
            {R.id.button25,0},
            {R.id.button31,0},
            {R.id.button32,0},
            {R.id.button33,0},
            {R.id.button34,0},
            {R.id.button35,0},
            {R.id.button41,0},
            {R.id.button42,0},
            {R.id.button43,0},
            {R.id.button44,0},
            {R.id.button45,0},
            {R.id.button51,0},
            {R.id.button52,0},
            {R.id.button53,0},
            {R.id.button54,0},
            {R.id.button55,0}};


    ArrayList<Integer> drawnNos=new ArrayList<Integer>();
    int[][] bingoCard= new int[5][5];
    int[] randomArray= new int[25];
    int [][] btnResources=new int[] []{{R.id.button11,R.id.button12,R.id.button13,R.id.button14,R.id.button15},
                                       {R.id.button21,R.id.button22,R.id.button23,R.id.button24,R.id.button25},
                                       {R.id.button31,R.id.button32,R.id.button33,R.id.button34,R.id.button35},
                                       {R.id.button41,R.id.button42,R.id.button43,R.id.button44,R.id.button45},
                                       {R.id.button51,R.id.button52,R.id.button53,R.id.button54,R.id.button55}};


    //ALL ROWS
    int []bingocase1=new int[]{R.id.button11,R.id.button12,R.id.button13,R.id.button14,R.id.button15};
    int []bingocase2=new int[]{R.id.button21,R.id.button22,R.id.button23,R.id.button24,R.id.button25};
    int []bingocase3=new int[]{R.id.button31,R.id.button32,R.id.button33,R.id.button34,R.id.button35};
    int []bingocase4=new int[]{R.id.button41,R.id.button42,R.id.button43,R.id.button44,R.id.button45};
    int []bingocase5=new int[]{R.id.button51,R.id.button52,R.id.button53,R.id.button54,R.id.button55};

    //ALL COLUMNS
    int []bingocase6=new int[]{R.id.button11,R.id.button21,R.id.button31,R.id.button41,R.id.button51};
    int []bingocase7=new int[]{R.id.button12,R.id.button22,R.id.button32,R.id.button42,R.id.button52};
    int []bingocase8=new int[]{R.id.button13,R.id.button23,R.id.button33,R.id.button43,R.id.button53};
    int []bingocase9=new int[]{R.id.button14,R.id.button24,R.id.button34,R.id.button44,R.id.button54};
    int []bingocase10=new int[]{R.id.button15,R.id.button25,R.id.button35,R.id.button45,R.id.button55};

    //ALL DIAGONALS
    int []bingocase11=new int[]{R.id.button11,R.id.button22,R.id.button33,R.id.button44,R.id.button55};
    int []bingocase12=new int[]{R.id.button15,R.id.button24,R.id.button33,R.id.button42,R.id.button51};

    //SPEACIAL CASE
    int []bingocase13=new int[]{R.id.button11,R.id.button15,R.id.button33,R.id.button51,R.id.button55};

    int [][] bingoCases=new int[][]
            {{R.id.button11,R.id.button12,R.id.button13,R.id.button14,R.id.button15},
            {R.id.button21,R.id.button22,R.id.button23,R.id.button24,R.id.button25},
            {R.id.button31,R.id.button32,R.id.button33,R.id.button34,R.id.button35},
            {R.id.button41,R.id.button42,R.id.button43,R.id.button44,R.id.button45},
            {R.id.button51,R.id.button52,R.id.button53,R.id.button54,R.id.button55},
            {R.id.button11,R.id.button21,R.id.button31,R.id.button41,R.id.button51},
            {R.id.button12,R.id.button22,R.id.button32,R.id.button42,R.id.button52},
            {R.id.button13,R.id.button23,R.id.button33,R.id.button43,R.id.button53},
            {R.id.button14,R.id.button24,R.id.button34,R.id.button44,R.id.button54},
            {R.id.button15,R.id.button25,R.id.button35,R.id.button45,R.id.button55},
            {R.id.button11,R.id.button22,R.id.button33,R.id.button44,R.id.button55},
            {R.id.button15,R.id.button24,R.id.button33,R.id.button42,R.id.button51},
            {R.id.button11,R.id.button15,R.id.button33,R.id.button51,R.id.button55}};

    boolean[] resultArray=new boolean[]{true,true,true,true,true,true,true,true,true,true,true,true,true};

    MediaPlayer gamemusic;
    MediaPlayer buttonClick;
    MediaPlayer winmusic;
    MediaPlayer losemusic;
    MediaPlayer tada;

    Timer timer;
    ProgressBar bingoprogressbar;
    TextView randomNoTextView;

    int timecount=1;
    int totalno=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        gamemusic=MediaPlayer.create(this,R.raw.music);
        buttonClick=MediaPlayer.create(this,R.raw.click);
        winmusic=MediaPlayer.create(this,R.raw.win);
        losemusic=MediaPlayer.create(this,R.raw.lose);
        tada=MediaPlayer.create(this,R.raw.tada);

        //gamemusic.start();

        timer=new Timer();
        bingoprogressbar= (ProgressBar) findViewById(R.id.progressBar);
        randomNoTextView= (TextView) findViewById(R.id.textView);
        bingoBtn= (Button) findViewById(R.id.button2);

        bingoprogressbar.setVisibility(View.INVISIBLE);


           array_Init();
            for(int i=0;i<randomArray.length;i++)
               {
                       switch (getColumnCount(i))
                        {
                            case 0:
                                min=1; max=15;
                                break;

                            case 1:
                                min=16; max=30;
                                break;

                            case 2:
                                min=31; max=45;
                                break;

                            case 3:
                                min=46; max=60;
                                break;

                            case 4:
                                min=61; max=75;
                                break;

                        }
                   randomNo=generateRandomNo(min,max);
                   for(int j=0;j<randomArray.length;j++)
                   {
                       while(randomArray[j]==randomNo) {
                           randomNo = generateRandomNo(min, max);
                           //break;
                       }

                   }
                   randomArray[i]=randomNo;
               }

        Arrays.sort(randomArray);

        //Log.i("ANISH", "START");
        for(int i=0;i<randomArray.length ;i++) {

            //Log.i("ANISH", String.valueOf(i)+"=>"+String.valueOf(randomArray[i]));
           //if(!checkArrayElementExists(randomArray[i]))
            //{

                int row= i%5;
                int column=i/5;

                //Log.i("ANISH", String.valueOf(row)+"=>"+String.valueOf(column));

                bingoCard[row][column]=randomArray[i];
           // }


        }
        //Log.i("ANISH", "END");

        for(int i=0;i<CARD_ROW_COUNT;i++) {
            for (int j = 0; j < CARD_COLUMN_COUNT; j++) {


                //bingoCard[i][j]=randomArray[i][j];
                //Log.i("ANISH",String.valueOf(bingoCard[i][j]));
                mybtn= (Button) findViewById(btnResources[i][j]);
                mybtn.setText(String.valueOf(bingoCard[i][j]));
                mybtn.setOnLongClickListener(this);
            }
        }

        btn= (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                tada.start();
                do{
                    randomNo=generateRandomNo(MININTEGER,MAXINTEGER);
                }while (drawnNos.contains(randomNo));

                drawnNos.add(randomNo);
                randomNoTextView.setText(String.valueOf(randomNo));

            }
        });

        bingoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bingoResult1=checkSameNo();
                boolean bingoResult2=checkForBingo();

                if(bingoResult1 && bingoResult2)
                {
                    winmusic.start();
                    Toast.makeText(MainActivity.this,"Congratulations!!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    losemusic.start();
                    Toast.makeText(MainActivity.this,"Bad Bingo", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean checkSameNo() {

        boolean sameFlag=true;

        for(int i=0;i<playerArray.length;i++)
        {
            if(playerArray[i][1]==1)
            {
                Button clickbtn= (Button) findViewById(playerArray[i][0]);
                int no=Integer.valueOf(clickbtn.getText().toString());
                Log.i("ANISH",String.valueOf(no));


                if(!drawnNos.contains(no))
                {
                    //Log.i("ANISH","FALSE");
                    sameFlag=false;
                    //return true;
                    Log.i("ANISH",String.valueOf(no));

                }
                else
                {
                    //Log.i("ANISH","TRUE");
                    //return false;

                }
            }
        }
        return sameFlag;
    }


    private boolean checkForBingo() {


        ArrayList<Integer> clickedArray=new ArrayList<Integer>();
        boolean bingoFlag=false;

        for(int i=0; i<playerArray.length;i++)
        {
            if(playerArray[i][1]==1)
            {
                clickedArray.add(playerArray[i][0]);
            }

        }

        if(checkElementExistsInArray(clickedArray,bingocase1))
        {
            bingoFlag=true;
            return bingoFlag;
        }

        if(checkElementExistsInArray(clickedArray,bingocase2))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase3))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase4))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase5))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase6))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase7))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase8))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase9))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase10))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase11))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase12))
        {
            bingoFlag=true;
            return bingoFlag;
        }
        if(checkElementExistsInArray(clickedArray,bingocase13))
        {
            bingoFlag=true;
            return bingoFlag;
        }






//        int index=0;
//
//        for(int j=0;j<13;j++)
//        {
//            for (int k=0;k<5;k++)
//            {
//                int id=bingoCases[j][k];
//
//                //Log.i("ANISH",String.valueOf(playerArray[id][1]));
//
//                for(int i=0; i<playerArray.length;i++)
//                {
//                    if(id==playerArray[i][0])
//                    {
//                        index=i;
//                        //Log.i("ANISH",String.valueOf(i));
//                        break;
//                    }
//
//                }
//
//                if(playerArray[index][1]!=1)
//                {
//                    resultArray[j]=false;
//                }
//                else
//                {
//                    resultArray[j]=true;
//                }
//            }
//        }

//        for (int i=0;i<resultArray.length;i++)
//        {
//            if(resultArray[i]==true)
//                bingoFlag=true;
//            else
//                bingoFlag=false;
//        }

        return bingoFlag;
    }

    private boolean checkElementExistsInArray(ArrayList<Integer> clickedArray, int[] bingocase) {

        boolean allexistFlag=true;

        for(int i=0; i<bingocase.length;i++)
        {
            if(!clickedArray.contains(bingocase[i])) {
                allexistFlag = false;
                break;
            }




        }

        return allexistFlag;
    }


    private int getColumnCount(int index) {

        return index % CARD_COLUMN_COUNT;
    }

    private void array_Init() {

        for(int i=0;i<CARD_ROW_COUNT;i++)
        {
            for (int j = 0; j < CARD_COLUMN_COUNT; j++)
            {
                //randomArray[i][j]=0;
                bingoCard[i][j]=0;
            }
        }
    }

    private boolean checkArrayElementExists(int element) {

        for(int i=0;i<CARD_ROW_COUNT;i++)
        {
            for(int j=0;j<CARD_COLUMN_COUNT;j++)
            {
                if(element==bingoCard[i][j])
                {
                    return true;
                }

            }
        }
        return false;
    }


    private boolean checkArrayElementExists(int element, int[][] cardArray) {

        Log.i("ANISH",String.valueOf(element));
        for(int i=0;i<CARD_ROW_COUNT;i++)
        {
            for(int j=0;j<CARD_COLUMN_COUNT;j++)
            {


                if(element==cardArray[i][j])
                {
                    return true;
                }

            }
        }

        return false;
    }


    private int generateRandomNo(int min, int max) {

        Random random=new Random();

        return random.nextInt((max - min) + 1) + min;

    }


    @Override
    protected void onPause() {
        super.onPause();
        gamemusic.release();
        timer.cancel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        gamemusic.start();
    }
/*@Override
            public void onClick(View v) {



                mybtn= (Button) findViewById(v.getId());
                //mybtn.setHapticFeedbackEnabled(false);
                Log.i("ANISH",String.valueOf(mybtn.getText()));
                if(!isPressed) {
                    mybtn.setBackground(getResources().getDrawable(R.drawable.cardwhitebg_pressed));
                    isPressed=true;
                }
                else
                {
                    mybtn.setBackground(getResources().getDrawable(R.drawable.cardwhitebg));
                    isPressed=false;
                }



            }
        */



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onLongClick(View v) {

       int index=0;
        mybtn= (Button) findViewById(v.getId());
        buttonClick.start();
        //Log.i("ANISH",String.valueOf(mybtn.getText()));

        for(int i=0; i<playerArray.length;i++)
        {
            if(v.getId()==playerArray[i][0])
            {
                index=i;
               // Log.i("ANISH",String.valueOf(i));
                break;
            }

        }

        if(playerArray[index][1]==0) {
            mybtn.setBackgroundResource(R.drawable.cardwhitebg_pressed);
            playerArray[index][1]=1;
        }
        else
        {
            mybtn.setBackgroundResource(R.drawable.cardwhitebg);
            playerArray[index][1]=0;
        }

        return true;
    }



/*
    @Override
    public boolean onTouch(View v, MotionEvent event) {


        if (event.getAction() == MotionEvent.ACTION_UP ) {
            mybtn= (Button) findViewById(v.getId());
            //mybtn.setHapticFeedbackEnabled(false);
            Log.i("ANISH",String.valueOf(mybtn.getText()));
            if(!isPressed) {
                mybtn.setBackground(getResources().getDrawable(R.drawable.cardwhitebg_pressed));
                isPressed=true;
            }
            else
            {
                mybtn.setBackground(getResources().getDrawable(R.drawable.cardwhitebg));
                isPressed=false;
            }
            return true;
        }


        return false;
    }
    */
}
