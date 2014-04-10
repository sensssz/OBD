package cn.edu.nju.software.obd.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.nju.software.obd.R;
import cn.edu.nju.software.obd.network.HttpClient;
import cn.edu.nju.software.obd.network.Url;
import cn.jpush.android.api.JPushInterface;

public class LoginActivity extends Activity {
    private EditText mUsernameEdit;
    private EditText mPasswordEdit;

    private MessageDigest mMD5Digest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsernameEdit = (EditText) findViewById(R.id.username);
        mPasswordEdit = (EditText) findViewById(R.id.password);
        Button loginButton = (Button) findViewById(R.id.login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = mUsernameEdit.getText().toString();
                final String password = mPasswordEdit.getText().toString();
                if (username.length() < 1) {
                    showMessage(R.string.username_empty);
                } else if (password.length() < 1) {
                    showMessage(R.string.password_empty);
                } else {
                    new AsyncTask<Void, Void, String>() {
                        @Override
                        protected String doInBackground(Void... voids) {
                            String MD5edUsername = MD5(username);
                            String MD5edPassword = MD5(password);
                            List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
                            parameters.add(new BasicNameValuePair("username", MD5edUsername));
                            parameters.add(new BasicNameValuePair("password", MD5edPassword));
                            try {
                                return HttpClient.getInstance().httpPost(Url.LOGIN_URL, parameters);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            return null;
                        }

                        @Override
                        protected void onPostExecute(String signInResult) {
                            if (signInResult == null) {
                                showMessage(R.string.connection_fail);
                                return;
                            }
                            String[] results = signInResult.split(",");
                            if (results[0].equals("1")) {
                                JPushInterface.setAlias(LoginActivity.this, results[1], null);
                                showMessage(R.string.login_success);
                                finish();
                            } else {
                                showMessage(R.string.login_fail);
                            }
                        }
                    }.execute();
                }
            }
        });
    }

    private void showMessage(int messageID) {
        Toast.makeText(this, messageID, Toast.LENGTH_SHORT).show();
    }

    private String MD5(String string) {
        if (mMD5Digest == null) {
            try {
                mMD5Digest = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        mMD5Digest.update(string.getBytes());
        return new String(mMD5Digest.digest());
    }
}
