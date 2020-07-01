package com.arbaelbarca.depedencymodule.model.modeltopheadlines;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseNewsTopHeadlines{

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("articles")
	private ArrayList<ArticlesItem> articles;

	@SerializedName("status")
	private String status;

	public void setTotalResults(int totalResults){
		this.totalResults = totalResults;
	}

	public int getTotalResults(){
		return totalResults;
	}

	public void setArticles(ArrayList<ArticlesItem> articles){
		this.articles = articles;
	}

	public ArrayList<ArticlesItem> getArticles(){
		return articles;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ResponseNewsTopHeadlines{" + 
			"totalResults = '" + totalResults + '\'' + 
			",articles = '" + articles + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}