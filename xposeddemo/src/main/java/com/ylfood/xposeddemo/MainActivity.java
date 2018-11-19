package com.ylfood.xposeddemo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText edtNo;
    private int testCode = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNo = findViewById(R.id.edtNo);
    }

    public void myClick(View view) {
        switch (view.getId()) {
            case R.id.btnTest:
                try {
                    int inputNo = Integer.parseInt(edtNo.getText().toString());
                    boolean isEqual = isEquals(inputNo);
                    if (isEqual) {
                        Toast.makeText(getApplicationContext(), "验证通过！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "验证不能通过！", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("EdtHooker", "testCode = " + testCode);
                        }
                    }, 5000);
                }
                break;
        }
    }

    /**
     * 通过Xposed修改该方法返回值，使之返回true恒成立
     *
     * @param inputNo
     * @return
     */
    private boolean isEquals(int inputNo) {
        return inputNo == 3;
    }
}
