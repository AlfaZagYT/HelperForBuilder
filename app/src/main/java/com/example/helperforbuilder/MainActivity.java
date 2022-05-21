package com.example.helperforbuilder;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected double Square = 0;
    private int SquareMaterial = 0;
    private int needMaterialCount = 0;
    private int needCountPackage = 0;

    protected int roomL = 0;
    protected int roomW = 0;
    private int materialL = 0;
    private int materialW = 0;
    private int materialC = 0;

    protected boolean protect1 = false;
    protected boolean protect2 = false;
    private boolean protect3 = false;
    private boolean protect4 = false;
    private boolean protect5 = false;

    protected EditText roomLength;
    protected EditText roomWidth;
    private EditText materialLength;
    private EditText materialWidth;
    private EditText materialCount;

    protected TextView textView;

    protected Button button;

    protected String text;
    protected String text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); {
//—— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— —— ——
            roomLength = findViewById(R.id.eRoomLength);
            roomWidth = findViewById(R.id.eRoomWidth);
            materialLength = findViewById(R.id.eMaterialLength);
            materialWidth = findViewById(R.id.eMaterialWidth);
            materialCount = findViewById(R.id.eMaterialCount);
            button = findViewById(R.id.button);
            textView = findViewById(R.id.result);
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
        }
    }

    private void protect(){
        if (roomLength.getText().toString().equals("")) {
            roomLength.setError("Заполните пустое поле!");
        } else {
            roomL = parseInt(roomLength.getText().toString());
            protect1 = true;
        }
        if (roomWidth.getText().toString().equals("")) {
            roomWidth.setError("Заполните пустое поле!");
        } else {
            roomW = parseInt(roomWidth.getText().toString());
            protect2 = true;
        }
        if (materialLength.getText().toString().equals("")) {
            materialLength.setError("Заполните пустое поле!");
        } else {
            materialL = parseInt(materialLength.getText().toString());
            protect3 = true;
        }
        if (materialWidth.getText().toString().equals("")) {
            materialWidth.setError("Заполните пустое поле!");
        } else {
            materialW = parseInt(materialWidth.getText().toString());
            protect4 = true;
        }
        if (materialCount.getText().toString().equals("")) {
            materialCount.setError("Заполните пустое поле!");
        } else {
            materialC = parseInt(materialCount.getText().toString());
            protect5 = true;
        }
    }

    protected void protectReset(){
        this.protect1 = false;
        this.protect2 = false;
        this.protect3 = false;
        this.protect4 = false;
        this.protect5 = false;
    }

    protected void math(){
        Square = roomL * roomW;
        SquareMaterial = materialL * materialW;
        needMaterialCount = (int) Square / SquareMaterial + 1;
        needCountPackage = needMaterialCount / materialC + 1;
        text = "На площадь " + Square / 10000 + " м²\n" +
                "Нужно " + needMaterialCount + " шт.\n" +
                "Или " + needCountPackage + " упаковок";
        text2 = "Площадь " + Square / 10000 + " м².";
    }
}