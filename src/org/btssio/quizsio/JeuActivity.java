package org.btssio.quizsio;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class JeuActivity extends Activity implements OnClickListener {

	TextView joueur;
	TextView laQuestion;
	RadioGroup groupQuestions;
	RadioButton[] reponses;
	Button resultat;
	int numero;
	String questions[][] = {
		{ "John McLane", "Pi�ge en eaux troubles", "Pulp Fiction", "Pi�ge de Cristal", "3" },
		{ "Martin McFly", "Mars Attacks !", "Spin City", "Retour vers le futur", "3" },
		{ "Alan Parrish", "Jumanji", "Good Morning, Vietnam", "Popeye", "1" }
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jeu);
		
		String message = this.getIntent().getExtras().getString("Joueur");
		numero = this.getIntent().getExtras().getInt("Numero");
		joueur = (TextView)this.findViewById(R.id.jouer);
		joueur.setText("A vous de jouer "+message+"...");
		
		// Initialisation des contr�les
		 laQuestion = (TextView)findViewById(R.id.txvQuestion);
		 laQuestion.setMinimumWidth(400);
		 groupQuestions = (RadioGroup)findViewById(R.id.rdgQuestion);
		 reponses = new RadioButton[3];
		 
		 // Cr�ation des boutons radio
		 reponses[0] = new RadioButton(this);
		 reponses[0].setTextColor(Color.parseColor("#1111FF"));
		 reponses[0].setMinimumWidth(400);
		 reponses[1] = new RadioButton(this);
		 reponses[1].setTextColor(Color.parseColor("#1111FF"));
		 reponses[1].setMinimumWidth(400);
		 reponses[2] = new RadioButton(this);
		 reponses[2].setTextColor(Color.parseColor("#1111FF")); 
		 reponses[2].setMinimumWidth(400);

		 // Affectation d'un ID aux boutons radio afin de savoir lequel est s�lectionn�
		 reponses[0].setId(0);
		 reponses[1].setId(1);
		 reponses[2].setId(2);

		 // Gestion du bouton
		 resultat = (Button)findViewById(R.id.btnReponse);
		 resultat.setOnClickListener(this);

		 // D�marrage du jeu
		 initQuestions(numero);
	}
	
	private void initQuestions(int numero) {
		 Log.i("numero",""+numero);
		 laQuestion.setText("Dans quel film le personnage de "+questions[numero][0]+ " joue ?");
		 reponses[0].setText(questions[numero][1]);
		 groupQuestions.addView(reponses[0]);
		 reponses[1].setText(questions[numero][2]);
		 groupQuestions.addView(reponses[1]);
		 reponses[2].setText(questions[numero][3]);
		 groupQuestions.addView(reponses[2]);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int retour = groupQuestions.getCheckedRadioButtonId();
		int repOk = Integer.parseInt(questions[numero][4]);
		
		if (retour+1 == repOk ) {
			Toast.makeText(this,"Bonne r�ponse !", Toast.LENGTH_LONG).show();
		} else {
			Toast.makeText(this,"Mauvaise r�ponse, c'�tait : "+questions[numero][repOk],Toast.LENGTH_LONG).show();
		}

		 // Jeu suivant
		Intent returnIntent = new Intent();
		returnIntent.putExtra("Numero",numero);
		setResult(RESULT_OK,returnIntent);
		finish();
	}
}
