package com.example.nyttest.network.model

import com.google.gson.annotations.SerializedName
import org.parceler.Parcel
import java.io.Serializable


@Parcel
data class ArticlesInfo(
    @SerializedName("status")
    var status: String? = "",
    @SerializedName("copyright")
    var copyright: String? = "",
    @SerializedName("num_results")
    var numResults: Int? = 0,
    @SerializedName("results")
    var results: MutableList<Results> = arrayListOf()
) : Serializable

@Parcel
data class MediaMetadata(
    @SerializedName("url")
    var url: String? = "",
    @SerializedName("format")
    var format: String? = "",
    @SerializedName("height")
    var height: Int? = 0,
    @SerializedName("width")
    var width: Int? = 0,
) : Serializable



@Parcel
data class Results(
    @SerializedName("uri") var uri: String? = "",
    @SerializedName("url") var url: String? = "",
    @SerializedName("id") var id: Double? = 0.0,
    @SerializedName("asset_id") var asset_id: Double? = 0.0,
    @SerializedName("source") var source: String? = "",
    @SerializedName("published_date") var published_date: String? = "",
    @SerializedName("updated") var updated: String? = "",
    @SerializedName("section") var section: String? = "",
    @SerializedName("subsection") var subsection: String? = "",
    @SerializedName("nytdsection") var nytdsection: String? = "",
    @SerializedName("adx_keywords") var adx_keywords: String? = "",
    @SerializedName("column") var column: String? = "",
    @SerializedName("byline") var byline: String? = "",
    @SerializedName("type") var type: String? = "",
    @SerializedName("title") var title: String? = "",
    @SerializedName("abstract") var abstract: String? = "",
    @SerializedName("des_facet") var des_facet: List<String> = arrayListOf(),
    @SerializedName("org_facet") var org_facet: List<String> = arrayListOf(),
    @SerializedName("per_facet") var per_facet: List<String> = arrayListOf(),
    @SerializedName("geo_facet") var geo_facet: List<String> = arrayListOf(),
    @SerializedName("media") var media: MutableList<Media> = arrayListOf(),
    @SerializedName("eta_id") var etaId: Int? = 0

) : Serializable


@Parcel
data class Media(
    @SerializedName("type") var type: String? = "",
    @SerializedName("subtype") var subtype: String? = "",
    @SerializedName("caption") var caption: String? = "",
    @SerializedName("copyright") var copyright: String? = "",
    @SerializedName("approved_for_syndication") var approved_for_syndication: Int? = 0,
    @SerializedName("media-metadata") var `media-metadata`: MutableList<MediaMetadata> = arrayListOf(),

    ) : Serializable