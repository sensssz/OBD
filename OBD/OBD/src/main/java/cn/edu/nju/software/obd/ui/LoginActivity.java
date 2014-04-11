package cn.edu.nju.software.obd.ui;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.edu.nju.software.obd.R;
import cn.jpush.android.api.InstrumentedActivity;

public class LoginActivity extends InstrumentedActivity {
    private EditText mUsernameEdit;
    private EditText mPasswordEdit;

    private MessageDigest mMD5Digest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            getActionBar().setTitle(getString(R.string.title_activity_login));
        }

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
                    showMessage(R.string.login_success);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
//                    new AsyncTask<Void, Void, String>() {
//                        @Override
//                        protected String doInBackground(Void... voids) {
//                            String MD5edUsername = MD5(username);
//                            String MD5edPassword = MD5(password);
//                            List<NameValuePair> parameters = new ArrayList<NameValuePair>(2);
//                            parameters.add(new BasicNameValuePair("username", MD5edUsername));
//                            parameters.add(new BasicNameValuePair("password", MD5edPassword));
//                            try {
//                                return HttpClient.getInstance().httpPost(Url.LOGIN_URL, parameters);
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            return null;
//                        }
//
//                        @Override
//                        protected void onPostExecute(String signInResult) {
//                            if (signInResult == null) {
//                                showMessage(R.string.connection_fail);
//                                return;
//                            }
//                            String[] results = signInResult.split(",");
//                            if (results[0].equals("1")) {
//                                JPushInterface.setAlias(LoginActivity.this, results[1], null);
//                                showMessage(R.string.login_success);
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                startActivity(intent);
//                                finish();
//                            } else {
//                                showMessage(R.string.login_fail);
//                            }
//                        }
//                    }.execute();
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
