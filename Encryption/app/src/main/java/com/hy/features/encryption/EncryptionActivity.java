package com.hy.features.encryption;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.hy.app.encryption.R;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;


public class EncryptionActivity extends Activity implements View.OnClickListener {
    private final String TAG = "060_EncryptionActivity";
    private Button encryptionBtn, decryptionBtn;// 加密，解密
    private EditText inputContent, outputEncryContent, outputDecryContent;// 需加密的内容，加密后的内容，解密后的内容

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsamain);
        initView();
    }

    private void initView() {
        encryptionBtn = (Button) findViewById(R.id.encryption_btn);
        decryptionBtn = (Button) findViewById(R.id.decryption_btn);
        encryptionBtn.setOnClickListener(this);
        decryptionBtn.setOnClickListener(this);

        inputContent = (EditText) findViewById(R.id.input_content);
        outputEncryContent = (EditText) findViewById(R.id.output_encrycontent);
        outputDecryContent = (EditText) findViewById(R.id.output_decrycontent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // 加密
            case R.id.encryption_btn:
                String source = inputContent.getText().toString().trim();
                Log.e(TAG, source);
                try {
                    Log.e(TAG, "进入");
                    // 从文件中得到公钥
                    InputStream inPublic = getAssets().open("rsa_public_key.pem");
                    PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
                    // 加密
                    byte[] encryptByte = RSAUtils.encryptData(source.getBytes(), publicKey);

                    // 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
                    String afterencrypt = Base64Utils.encode(encryptByte);
                    Log.e(TAG, afterencrypt);
                    outputEncryContent.setText(afterencrypt);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            // 解密
            case R.id.decryption_btn:
                String encryptContent = outputEncryContent.getText().toString().trim();
                try {
                    // 从文件中得到私钥
                    InputStream inPrivate = getResources().getAssets().open("pkcs8_rsa_private_key.pem");
                    PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
                    // 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
                    byte[] decryptByte = RSAUtils.decryptData(Base64Utils.decode(encryptContent), privateKey);
                    String decryptStr = new String(decryptByte);
                    Log.e(TAG, decryptStr);
                    outputDecryContent.setText(decryptStr);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

}
