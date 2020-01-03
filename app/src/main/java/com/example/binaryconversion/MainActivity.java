package com.example.binaryconversion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 当前视图
        View view = View.inflate(getApplicationContext(), R.layout.activity_main, null);

//        ₘₙⁿ
//        ▏n
//        █▏　､⺍
//        █▏ ⺰ʷʷｨ
//        █◣▄██◣
//        ◥██████▋
//        　◥████ █▎
//        　　███▉ █▎
//        　◢████◣⌠ₘ℩
//        　　██◥█◣\≫
//        　　██　◥█◣
//        　　█▉　　█▊
//        　　█▊　　█▊
//        　　█▊　　█▋
//        　　 █▏　　█▙
//        　　 █
//
//          止まるじゃねえぞ
//            不要停下来啊

        // 当前进制
        Spinner inputBinary = findViewById(R.id.inputBinary);
        final int[] input_binary = new int[1];
        setSearchAdapter(view, R.array.itemBinary, inputBinary, input_binary);
        // 默认十进制
        inputBinary.setSelection(2, true);

        // 目标进制
        Spinner outputBinary = findViewById(R.id.outputBinary);
        final int[] output_binary = new int[1];
        setSearchAdapter(view, R.array.itemBinary, outputBinary, output_binary);
        // 默认二进制
        outputBinary.setSelection(0, true);

        // 当前数据
        final EditText inputNum = findViewById(R.id.inputNum);
        // 目标数据
        final TextView outputNum = findViewById(R.id.outputNum);

        // 点击屏幕清空焦点
        findViewById(R.id.rootView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.rootView:
                        InputMethodManager imm = (InputMethodManager)
                                getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        inputNum.clearFocus();
                        break;
                }
            }
        });

        // 开始按钮
        Button start = findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_num = String.valueOf(inputNum.getText());
                int from = searchMapping(input_binary[0]);
                int to = searchMapping(output_binary[0]);
//                  ﹎ ┈ ┈ .o┈ ﹎  ﹎.. ○
//                  ﹎┈﹎ ●  ○ .﹎ ﹎o▂▃▅▆
//                  ┈ ┈ /█\/▓\ ﹎ ┈ ﹎﹎ ┈ ﹎
//                  ▅▆▇█████▇▆▅▃▂┈﹎
                if (Calculate.inputRight(input_num, from)) {
                    // 计算结果，将字母转换为大写，输出到显示文本框上
                    outputNum.setText(Calculate.binaryChange(input_num, from, to).toUpperCase());
                }
                else {
                    Toast.makeText(MainActivity.this, "有输入误哦|ू･ω･` )", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /* spinner 适配器 */
    private void setSearchAdapter(View view, int textArrayResId, Spinner spinner, final int[] search) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(), textArrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i >= 0)
                    search[0] = i;
            }
            public void onNothingSelected(AdapterView<?> adapterView)
            {   }
        });
    }

    /* 将选项转换为进制数 */
    private int searchMapping(int search){
        int binary = 10;
        switch (search) {
            case 0:
                binary = 2;
                break;
            case 1:
                binary = 8;
                break;
            case 2:
                binary = 10;
                break;
            case 3:
                binary = 16;
                break;
        }
        return binary;
    }
}
