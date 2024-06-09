package com.example.navigationdrawer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class LanguageUtils {

    public static void setLanguage(Context context, String language, int item) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());

        // Save language
        SharedPreferences.Editor editor = context.getSharedPreferences("LANGUAGE_SETTINGS", Context.MODE_PRIVATE).edit();
        editor.putString("language", language);
        editor.putInt("item", item);
        editor.apply();
    }

    public static void loadLanguage(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("LANGUAGE_SETTINGS", Context.MODE_PRIVATE);
        String language = sharedPreferences.getString("language", "en");
        int item = sharedPreferences.getInt("item", 0);
        setLanguage(context, language, item);
    }

    public static void showLanguageDialog(Context context) {
        final String[] languageList = {"English", "বাংলা"};

        SharedPreferences sharedPreferences = context.getSharedPreferences("LANGUAGE_SETTINGS", Context.MODE_PRIVATE);
        int item = sharedPreferences.getInt("item", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.select_language));
        builder.setSingleChoiceItems(languageList, item, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                if (position == 0) {
                    setLanguage(context, "en", 0);
                    if (context instanceof AppCompatActivity) {
                        ((AppCompatActivity) context).recreate();
                    }
                } else if (position == 1) {
                    setLanguage(context, "bn", 1);
                    if (context instanceof AppCompatActivity) {
                        ((AppCompatActivity) context).recreate();
                    }
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
