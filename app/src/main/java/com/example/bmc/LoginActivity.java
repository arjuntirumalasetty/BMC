package com.example.bmc;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.auth.api.Auth;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import businessojects.Coach;
import butterknife.ButterKnife;
import cache.UICacheImpl;
import cache.UiCache;
import restEndPoints.RestCoachHandler;

import com.example.bmc.globalvariable.GlobalClass;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener,View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private static final String REG_USER = "reg";
    private TextView mStatusTextView;
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    private ProgressDialog mProgressDialog;
    private String emailOnLogin;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    UiCache cache = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    try{
        cache = UICacheImpl.getInstance((GlobalClass)getApplicationContext());

     /*   FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);*/

        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        findViewById(R.id.btn_login_with_gmail).setOnClickListener(this);
        //findViewById(R.id.nav_logOut).setOnClickListener(this);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton) findViewById(R.id.btn_login_with_gmail);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        sharedPreferences = getApplicationContext().getSharedPreferences(REG_USER, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }catch (Exception e){
        Log.e("LoginActivity",e.getMessage());
    }
    }

    private void callMainActivity() {
        /*Log.i(TAG, "Login>>>>>>");
        *//*AsyncTask<Void, Void, Coach> execute = new HttpRequestTask().execute();
        try {
            Log.i("Email id", execute.get().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*//*
*/
        Intent mainActivityIntern = new Intent(getBaseContext(),MainActivity.class);
        startActivity(mainActivityIntern);
        overridePendingTransition(R.animator.activity_open_scale,R.animator.activity_close_translate);
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void signOut() {

        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        // [START_EXCLUDE]
                        Log.i("a","Going to update UI to false>>>>>>>>>..");
                        try {
                            updateUI(false, null);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        // [END_EXCLUDE]
                    }
                });
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        // An unresolvable error has occurred and Google APIs (including Sign-In) will not
        // be available.
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
        Log.i("requestCode", "requestCode>>>>>>>>>>>>>>>>>."+requestCode);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
                handleSignInResult(result);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
        try{

            if(globalVariable != null && "LogOut".equals(globalVariable.getLogout())) {
                globalVariable.setLogout("");
                //signOut();

            } else {
                if (opr.isDone()) {
                    // If the user's cached credentials are valid, the OptionalPendingResult will be "done"
                    // and the GoogleSignInResult will be available instantly.
                    Log.i(TAG, "Got cached sign-in>>>>>>>>");
                    GoogleSignInResult result = opr.get();
                    handleSignInResult(result);
                } else {
                    // If the user has not previously signed in on this device or the sign-in has expired,
                    // this asynchronous branch will attempt to sign in the user silently.  Cross-device
                    // single sign-on will occur in this branch.
                    Log.i("dd","In cross device sign in>>>>>>>>>>>>");
                    showProgressDialog();
                    opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                        @Override
                        public void onResult(GoogleSignInResult googleSignInResult) {
                            hideProgressDialog();
                            try {
                                handleSignInResult(googleSignInResult);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }catch (IOException e){
            Log.e("IOException",e.getMessage());
        }
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) throws IOException {
        Log.i(TAG, "handleSignInResult>>>>>>>:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            updateUI(true, acct);
        } else {
            // Signed out, show unauthenticated UI.
            updateUI(false, null);
        }
    }

    private void updateUI(boolean signedIn, GoogleSignInAccount account) throws IOException {

        if (signedIn) {
            this.emailOnLogin = account.getEmail();
            Log.i("emailOnLogin", ">>"+emailOnLogin+">>"+account.getDisplayName());
            final GlobalClass globalVariable = (GlobalClass) getApplicationContext();
            Coach coach = new Coach();
            coach.setLoginEmail(emailOnLogin);
            Coach existingCoach = RestCoachHandler.getInstance().getCoach(coach);
            if(existingCoach == null) {
                Log.i("", "Existing Coach is null so creating one>>");
                cache.writeCoach(getApplicationContext(),coach);
                coach.setGoogleDisplayName(account.getDisplayName());
                editor.putString(getString(R.string.email_id),emailOnLogin);
                existingCoach = RestCoachHandler.getInstance().registerCoach(coach);
                editor.commit();
            }
            Log.i("existingCoach", ">>"+existingCoach.getLoginEmail());
            globalVariable.setCoach(existingCoach);
            globalVariable.setLoginEmail(existingCoach.getLoginEmail());
            globalVariable.setGoogleProfileName(existingCoach.getGoogleDisplayName());
            callMainActivity();
        } else {

            //mStatusTextView.setText(R.string.signed_out);
/*
            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);*/
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_with_gmail:
                signIn();
                break;
            case R.id.nav_logOut:
                Log.i("Sign out", ">>>>>Is it really coming here");
                signOut();
                break;
        }
    }
}
