package com.adgvcxz.diycode.binding

import android.databinding.BindingAdapter
import android.net.Uri
import android.text.TextUtils
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.common.ImageDecodeOptions
import com.facebook.imagepipeline.common.ResizeOptions
import com.facebook.imagepipeline.request.ImageRequest
import com.facebook.imagepipeline.request.ImageRequestBuilder
import io.reactivex.Observable


/**
 * zhaowei
 * Created by zhaowei on 2017/2/11.
 */
@BindingAdapter(value = *arrayOf("imageUrl", "thumbnail", "maxSize"), requireAll = false)
fun SimpleDraweeView.loadImage(imageUrl: String, thumbnail: String?, maxSize: Int) =
        Observable.just(ImageDecodeOptions.newBuilder().setDecodePreviewFrame(true).build())
                .map { options ->
                    ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl))
                            .setImageDecodeOptions(options)
                            .setProgressiveRenderingEnabled(true)

                }
                .map { builder ->
                    if (maxSize != 0) {
                        val size = this.context.resources.getDimensionPixelSize(maxSize)
                        if (size != 0) {
                            builder.resizeOptions = ResizeOptions(size, size)
                        } else {
                            builder.resizeOptions = ResizeOptions(maxSize, maxSize)
                        }
                    }
                    builder
                }
                .map { builder ->
                    if (maxSize != 0) {
                        val size = this.context.resources.getDimensionPixelSize(maxSize)
                        if (size != 0) {
                            builder.resizeOptions = ResizeOptions(size, size)
                        } else {
                            builder.resizeOptions = ResizeOptions(maxSize, maxSize)
                        }
                    }
                    builder
                }
                .map { builder ->
                    var lowRequest: ImageRequest? = null
                    if (!TextUtils.isEmpty(thumbnail)) {
                        lowRequest = ImageRequest.fromUri(thumbnail)
                    }
                    Fresco.newDraweeControllerBuilder()
                            .setImageRequest(builder.build())
                            .setLowResImageRequest(lowRequest)
                            .setOldController(this.controller)
                            .setAutoPlayAnimations(true)
                            .build()
                }
                .subscribe { controller ->
                    this.controller = controller
                }!!