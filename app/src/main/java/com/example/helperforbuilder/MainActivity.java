package com.example.helperforbuilder;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static java.lang.Integer.parseInt;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    protected static SharedPreferences mySavesSP;
    protected static SharedPreferences.Editor editor;
    protected static final String APP_PREFERENCES = "mySaves";
    protected static final String APP_PREFERENCES_TITLE = "Title";
    protected static final String APP_PREFERENCES_TEXT = "Text";
    protected static final String APP_PREFERENCES_SIZE = "Size";

    //—— —— —— —— —— —— —— —— —— Объявление переменных  —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——
    private TextView textView;

    private Button button, buttonSave;

    private double Square = 0;

    private long roomL,
            roomW,
            materialL,
            materialW,
            materialC,
            SquareMaterial,
            needMaterialCount,
            needCountPackage = 0;

    private boolean protect1,
            protect2,
            protect3,
            protect4,
            protect5 = false;

    private EditText roomLength,
            roomWidth,
            materialLength,
            materialWidth,
            materialCount;

    private String text,
            resultText1,
            resultText2,
            resultText2_1,
            resultText3,
            resultText3_1,
            meterInSquare,
            error;

    private final DecimalFormat formattedDouble = new DecimalFormat("###,###,###,###,###,##0.##");

    InputMethodManager inputMethodManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
//—— —— —— —— —— —— —— —— —— Определение переменных  —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——
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

            inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
//—— —— —— —— —— —— —— —— —— Кнопка подсчёта —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    protect();
                    math(protect1, protect2, protect3, protect4, protect5);
                    textView.setText(text);
                    hideKeyboard(MainActivity.this);
                }
            });
//—— —— —— —— —— —— —— —— —— Кнопка сохранения —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——
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
                            save(titleDialog, textDialog);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            });
        }
    }

//—— —— —— —— —— —— —— —— —— Защита и подсчёт —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——

    private void protect() {
        if (roomLength.getText().toString().equals("")) {
            roomLength.setError(error);
        } else {
            roomL = parseInt(roomLength.getText().toString());
            this.protect1 = true;
        }
        if (roomWidth.getText().toString().equals("")) {
            roomWidth.setError(error);
        } else {
            roomW = parseInt(roomWidth.getText().toString());
            this.protect2 = true;
        }
        if (materialLength.getText().toString().equals("")) {
            materialLength.setError(error);
        } else {
            materialL = parseInt(materialLength.getText().toString());
            this.protect3 = true;
        }
        if (materialWidth.getText().toString().equals("")) {
            materialWidth.setError(error);
        } else {
            materialW = parseInt(materialWidth.getText().toString());
            this.protect4 = true;
        }
        if (!materialCount.getText().toString().equals("") && !materialCount.getText().toString().equals("0")) {
            materialC = parseInt(materialCount.getText().toString());
            this.protect5 = true;
        }
    }

    private void protectReset() {
        this.protect1 = false;
        this.protect2 = false;
        this.protect3 = false;
        this.protect4 = false;
        this.protect5 = false;
    }

    private void math(boolean protect1, boolean protect2, boolean protect3, boolean protect4, boolean protect5) {
        Square = roomL * roomW;
        SquareMaterial = materialL * materialW;
        if (protect1 && protect2 && protect3 && protect4 && protect5) {
            needMaterialCount = (int) Math.ceil(Square / SquareMaterial);
            needCountPackage = (int) Math.ceil((Square / SquareMaterial) / materialC);
            text = resultText1 + " " + formattedDouble.format(Square / 10000) + " " + meterInSquare + "\n" +
                    resultText2 + " " + formattedDouble.format(needMaterialCount) + " " + resultText2_1 + "\n" +
                    resultText3 + " " + formattedDouble.format(needCountPackage) + " " + resultText3_1;
        } else if (protect1 && protect2 && protect3 && protect4 && !protect5) {
            needMaterialCount = (int) Math.ceil(Square / SquareMaterial);
            text = resultText1 + " " + formattedDouble.format(Square / 10000) + " " + meterInSquare + "\n" +
                    resultText2 + " " + formattedDouble.format(needMaterialCount) + " " + resultText2_1;
        }
        protectReset();
    }


//—— —— —— —— —— —— —— —— —— Menu —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setIntent(new Intent(this, History.class));
        return super.onOptionsItemSelected(item);
    }

//—— —— —— —— —— —— —— —— —— Shared Preferences —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——

    public int getSize() {
        return mySavesSP.getInt(APP_PREFERENCES_SIZE, 0);
    }

    public void sizeUp() {
        editor.putInt(APP_PREFERENCES_SIZE, getSize() + 1);
    }

    public void sizeDown() {
        editor.putInt(APP_PREFERENCES_SIZE, getSize() - 1);
    }

    //—— —— —— —— —— —— —— —— —— Сохранение и удаление —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——
    protected void save(EditText titleDialog, EditText textDialog) {
        if (titleDialog.getText().toString().equals("")) {
            titleDialog.setText(getString(R.string.titleSaved));
        }
        if (textDialog.getText().toString().equals("")) {
            textDialog.setText(getString(R.string.textSaved));
        }
        sizeUp();
        editor.putString(APP_PREFERENCES_TITLE + getSize(), titleDialog.getText().toString());
        editor.putString(APP_PREFERENCES_TEXT + getSize(), textDialog.getText().toString());
        editor.apply();
    }

    public static void delete(int position) {
        editor.remove(APP_PREFERENCES_TITLE + position);
        editor.remove(APP_PREFERENCES_TEXT + position);
        editor.putInt(APP_PREFERENCES_SIZE, mySavesSP.getInt(APP_PREFERENCES_SIZE, 0) - 1);
        editor.apply();
    }

    //—— —— —— —— —— —— —— —— —— Другие методы —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——
    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (activity.getCurrentFocus() != null) {
            if (activity.getCurrentFocus().getWindowToken() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }
}