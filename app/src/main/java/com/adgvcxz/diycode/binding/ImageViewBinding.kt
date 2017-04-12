package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.adgvcxz.diycode.util.CircleTransform
import com.squareup.picasso.Picasso
import io.reactivex.Observable


/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */
//@BindingAdapter(value = *arrayOf("imageUrl", "thumbnail", "maxSize"), requireAll = false)
//fun SimpleDraweeView.loadImage(imageUrl: String?, thumbnail: String?, maxSize: Int) =
//        Observable.just(ImageDecodeOptions.newBuilder().setDecodePreviewFrame(true).build())
//                .filter { !TextUtils.isEmpty(imageUrl) }
//                .map { options ->
//                    ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
//                            .setImageDecodeOptions(options)
//                            .setProgressiveRenderingEnabled(true)
//
//                }
//                .map { builder ->
//                    if (maxSize != 0) {
//                        val size = this.context.resources.getDimensionPixelSize(maxSize)
//                        if (size != 0) {
//                            builder.resizeOptions = ResizeOptions(size, size)
//                        } else {
//                            builder.resizeOptions = ResizeOptions(maxSize, maxSize)
//                        }
//                    }
//                    builder
//                }
//                .map { builder ->
//                    if (maxSize != 0) {
//                        val size = this.context.resources.getDimensionPixelSize(maxSize)
//                        if (size != 0) {
//                            builder.resizeOptions = ResizeOptions(size, size)
//                        } else {
//                            builder.resizeOptions = ResizeOptions(maxSize, maxSize)
//                        }
//                    }
//                    builder
//                }
//                .map { builder ->
//                    var lowRequest: ImageRequest? = null
//                    if (!TextUtils.isEmpty(thumbnail)) {
//                        lowRequest = ImageRequest.fromUri(thumbnail)
//                    }
//                    Fresco.newDraweeControllerBuilder()
//                            .setImageRequest(builder.build())
//                            .setLowResImageRequest(lowRequest)
//                            .setOldController(this.controller)
//                            .setAutoPlayAnimations(true)
//                            .build()
//                }
//                .subscribe { controller ->
//                    this.controller = controller
//                }!!

@BindingAdapter(value = *arrayOf("imageUrl", "placeholder", "maxSize", "isCircle"), requireAll = false)
fun ImageView.loadImage(imageUrl: String?, @DrawableRes placeholder: Int?, maxSize: Int?, isCircle: Boolean?) {
    Observable.just(Picasso.with(context))
            .map { it.load(imageUrl) }
            .map {
                if (placeholder != null) {
                    it.placeholder(placeholder)
                }
                it
            }
            .map {
                if (maxSize != null) {
                    val size = this.context.resources.getDimensionPixelSize(maxSize)
                    if (size != 0) {
                        it.resize(size, size)
                    } else {
                        it.resize(maxSize, maxSize)
                    }
                }
                it
            }
            .map {
                if (isCircle != null && isCircle) {
                    it.transform(CircleTransform())
                }
                it
            }
            .subscribe {
                it.into(this)
            }
}