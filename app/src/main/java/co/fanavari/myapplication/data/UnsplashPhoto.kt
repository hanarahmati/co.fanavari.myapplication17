package co.fanavari.myapplication.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val description: String,
    val user: UnsplashUser,
    val urls: UnsplashUrls
): Parcelable {
}
@Parcelize
data class UnsplashUrls(
    val raw: String,
    val regular: String,
    val thumb: String,
): Parcelable{

}

@Parcelize
data class UnsplashUser(
    val username: String,
    val name: String
) : Parcelable{

}
