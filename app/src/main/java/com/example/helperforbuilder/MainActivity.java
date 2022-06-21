package com.example.helperforbuilder;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences mySavesSP;
    public static final String APP_PREFERENCES = "mySaves";
    public static final String APP_PREFERENCES_TITLE = "Title";
    public static final String APP_PREFERENCES_TEXT = "Text";
    public static final String APP_PREFERENCES_SIZE = "Size";
    public static SharedPreferences.Editor editor;

    private double Square = 0;
    private int SquareMaterial = 0;
    private int needMaterialCount = 0;
    private int needCountPackage = 0;

    private int roomL = 0;
    private int roomW = 0;
    private int materialL = 0;
    private int materialW = 0;
    private int materialC = 0;

    private boolean protect1 = false;
    private boolean protect2 = false;
    private boolean protect3 = false;
    private boolean protect4 = false;
    private boolean protect5 = false;

    private EditText roomLength;
    private EditText roomWidth;
    private EditText materialLength;
    private EditText materialWidth;
    private EditText materialCount;

    private TextView textView;

    private Button button;
    private Button buttonSave;

    private String text;
    private String resultText1;
    private String resultText2;
    private String resultText2_1;
    private String resultText3;
    private String resultText3_1;
    private String meterInSquare;
    private String error;

    private final DecimalFormat formattedDouble = new DecimalFormat("#0.##");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
//—— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——
            mySavesSP = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
            editor = mySavesSP.edit();

            roomLength = findViewById(R.id.eRoomLength);
            roomWidth = findViewById(R.id.eRoomWidth);
            materialLength = findViewById(R.id.eMaterialLength);
            materialWidth = findViewById(R.id.eMaterialWidth);
            materialCount = findViewById(R.id.eMaterialCount);
            button = findViewById(R.id.button);
            buttonSave = findViewById(R.id.buttonSave);
            textView = findViewById(R.id.result);

            resultText1 = getString(R.string.result1);
            resultText2 = getString(R.string.result2);
            resultText2_1 = getString(R.string.result2_1);
            resultText3 = getString(R.string.result3);
            resultText3_1 = getString(R.string.result3_1);
            meterInSquare = getString(R.string.metersInSquare);
            error = getString(R.string.error);
//—— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    protect();
                    if (protect1 && protect2 && protect3 && protect4 && protect5) {
                        math();
                        textView.setText(text);
                        protectReset();
                    }
                }
            });
            buttonSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Dialog dialog = new Dialog(MainActivity.this);
                    dialog.setContentView(R.layout.dialog_save);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    EditText titleDialog = dialog.findViewById(R.id.Title);
                    EditText textDialog = dialog.findViewById(R.id.Text);

                    textDialog.setText(text);

                    Button button = dialog.findViewById(R.id.Save);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            sizeUp();
                            editor.putString(APP_PREFERENCES_TITLE + getSize(), titleDialog.getText().toString());
                            editor.putString(APP_PREFERENCES_TEXT + getSize(), textDialog.getText().toString());
                            editor.apply();
                            Log.i("SaveTest", "onClick: Clicked");
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }

    private void protect() {
        if (roomLength.getText().toString().equals("")) {
            roomLength.setError(error);
        } else {
            roomL = parseInt(roomLength.getText().toString());
            protect1 = true;
        }
        if (roomWidth.getText().toString().equals("")) {
            roomWidth.setError(error);
        } else {
            roomW = parseInt(roomWidth.getText().toString());
            protect2 = true;
        }
        if (materialLength.getText().toString().equals("")) {
            materialLength.setError(error);
        } else {
            materialL = parseInt(materialLength.getText().toString());
            protect3 = true;
        }
        if (materialWidth.getText().toString().equals("")) {
            materialWidth.setError(error);
        } else {
            materialW = parseInt(materialWidth.getText().toString());
            protect4 = true;
        }
        if (materialCount.getText().toString().equals("")) {
            materialCount.setError(error);
        } else {
            materialC = parseInt(materialCount.getText().toString());
            protect5 = true;
        }
    }

    protected void protectReset() {
        this.protect1 = false;
        this.protect2 = false;
        this.protect3 = false;
        this.protect4 = false;
        this.protect5 = false;
    }

    protected void math() {
        Square = roomL * roomW;
        SquareMaterial = materialL * materialW;
        needMaterialCount = (int) Math.ceil(Square / SquareMaterial);
        needCountPackage = (int) Math.ceil((Square / SquareMaterial) / materialC);
        text = resultText1 + " " + formattedDouble.format(Square / 10000) + " " + meterInSquare + "\n" +
                resultText2 + " " + needMaterialCount + " " + resultText2_1 + "\n" +
                resultText3 + " " + needCountPackage + " " + resultText3_1;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setIntent(new Intent(this, History.class));
//        switch (item.getItemId()) {
//            case R.id.itemHistory:
//                startActivity(new Intent(this, History.class));
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
        return super.onOptionsItemSelected(item);
    }

    public int getSize() {
        return mySavesSP.getInt(APP_PREFERENCES_SIZE, 0);
    }

    public void sizeUp() {
        editor.putInt(APP_PREFERENCES_SIZE, getSize() + 1);
    }

    public void sizeDown() {
        editor.putInt(APP_PREFERENCES_SIZE, getSize() - 1);
    }

    public static void delete(int position) {
        try {
            editor.remove(APP_PREFERENCES_TITLE + position);
            editor.remove(APP_PREFERENCES_TEXT + position);
            editor.putInt(APP_PREFERENCES_SIZE, mySavesSP.getInt(APP_PREFERENCES_SIZE, 0) - 1);
            editor.apply();
            Log.i("STATUS_SYSTEM", "IT`S WORKING???");
        } catch (Exception e) {
            Log.i("STATUS_SYSTEM", "delete: ", e);
        }
    }
}