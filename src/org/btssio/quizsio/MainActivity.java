package org.btssio.quizsio;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {

	Button depart;
	String prenom;
	Intent intentJeu;
	EditText input;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        depart = (Button)this.findViewById(R.id.button_go);
        depart.setOnClickListener(this);
        input = (EditText) findViewById(R.id.editText1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	@Override
	public void onClick(View v) {
		intentJeu = new Intent(this,JeuActivity.class); 
		prenom = input.getText().toString();
		if (prenom.matches("")) {
			Toast.makeText(this,"Le prénom est vide", Toast.LENGTH_SHORT).show();
		}
		else {
			lancementJeu(0);
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode > 1) {
			// Si l'on veut que l'application s'arrête...
			// finish();
		} else {
			lancementJeu(requestCode+1);
		}
	}
	
	public void lancementJeu(int num) {
		intentJeu.putExtra("Joueur",prenom);
		intentJeu.putExtra("Numero",num);
		this.startActivityForResult(intentJeu, num);
	}
}
