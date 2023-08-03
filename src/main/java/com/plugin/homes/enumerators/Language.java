package com.plugin.homes.enumerators;

/*
 *
 *@author Pablo Hermida Gómez DAM G1
 *
 */
public enum Language {

        ENGLISH("english"),
        SPANISH("spanish");

        private String language;

        Language(String language) {
            this.language = language;
        }

        //Function that returns the language based on the string
        public static Language getLanguage(String language){
            for (Language languages : Language.values()){
                if (languages.language.equalsIgnoreCase(language)){
                    return languages;
                }
            }
            return null;
        }

        public String getLanguage() {
            return language;
        }
}
