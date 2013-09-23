public class Sounds extends Activity implements OnClickListener
{

    MediaPlayer click;
    MediaPlayer trail_sounds;
    Boolean playing = false;
    //Puts click listeners on the button objects in the scene
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //audio found in res/raw/ directiory
        click = MediaPlayer.create(this, R.raw.notifictation);
        trail_sounds = MediaPlayer.create(this, R.raw.Trail_Music);
        //directory for buttons is default creation
        final Button cick_b = (Button) findViewById(R.id.click_b);
        button1.setOnClickListener(this);

        final Button play_b = (Button) findViewById(R.id.play_b);
        button1.setOnClickListener(this);
    }
    //Plays sound when clicked
    @Override
    public void onClick(View v) 
    {
        switch(v.getId()) 
        {
        case R.id.click_b:
            click.start();
            break;
        case R.id.play_b:
            if(playing == false)
            {
                trail_sounds.start();
                playing = true;
            }
            else
                trail_sounds.stop();
                playing = false;
            break;
        }
    }
    //change of scene will destroy media player
    @Override
    protected void onDestroy()
    {
        click.release();
        trail_sounds.release();
        super.onDestroy();
    }
}
