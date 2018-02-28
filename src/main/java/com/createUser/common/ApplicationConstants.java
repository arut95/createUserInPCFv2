package com.createUser.common;


public class ApplicationConstants {
	public enum ProgLanguage { DOTNET, JAVA}
	public enum ActivationStatus { NOTACTIVATED,TOBEACTIVATED,ACTIVATING,ACTIVATED,DELETED,DELETING}
	public enum EmailTemplte { ACCOUNTEMAIL,ERROREMAIL,REGISTRATIONEMAIL,SUPPORTTEAMEMAIL,DELETEREMAINDEREMAIL,DELETEACCOUNTEMAIL,LIMITEMAIL}
	public enum EMAILSTATUS { SENT, NOTSENT}	
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";
	public static final String ACCESS_TOKEN ="access_token" ;
	public static final String OAUTHGRANTTYPE= "password";
	public static final String APITOKEN = "apitoken";
}



