package thesonid.com.theeurovisionapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static thesonid.com.theeurovisionapp.R.id.winners;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView winners = (TextView) findViewById(R.id.winners);
        TextView bio = (TextView) findViewById(R.id.bio);
        TextView youtube = (TextView) findViewById(R.id.youtube);
        TextView web = (TextView) findViewById(R.id.web);
        TextView league= (TextView) findViewById(R.id.league);


        winners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent winnersIntent = new Intent(MainActivity.this,WinnersActivity.class);
                startActivity(winnersIntent);
            }
        });

        bio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bioIntent = new Intent(MainActivity.this,BioActivity.class);
                startActivity(bioIntent);
            }
        });

        web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://eurovision.tv";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.youtube.com/user/eurovision";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        league.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent leagueIntent = new Intent(MainActivity.this,LeagueActivity.class);
                startActivity(leagueIntent);
            }
        });

    }
}
