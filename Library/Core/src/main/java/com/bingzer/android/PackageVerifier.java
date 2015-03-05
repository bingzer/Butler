/**
 * Copyright 2013 Ricky Tobing
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bingzer.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.security.cert.X509Certificate;

/**
 * Used to verify a pacakge name against specified public key
 */
@SuppressWarnings("UnusedDeclaration")
public final class PackageVerifier {
	
	private boolean checked = false;
	private boolean verified = false;
	private String packageName;
	private PublicKey publicKey;
	
	public PackageVerifier(String packageName){
		this.packageName = packageName;
	}
	
	public void setPublicKey(int resourceId){
		setPublicKey(Res.readRaw(resourceId));
	}
	
	public void setPublicKey(CharSequence pubKeyBase64){
		try{
			byte[] pubKeyBytes = android.util.Base64.decode(pubKeyBase64.toString(), android.util.Base64.DEFAULT);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(pubKeyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			PublicKey pubKey = keyFactory.generatePublic(keySpec);
			setPublicKey(pubKey);
		}
		catch (Throwable e){
			// ignore
		}
	}
	
	public void setPublicKey(PublicKey pubKey){
		this.publicKey = pubKey;
	}
	
	public boolean isVerified(){
		return verified;
	}
	
	public boolean isChecked(){
		return checked;
	}
	
	public boolean verify(Context context, boolean recheck){
		if(recheck) {
			verified = false;
			checked = false;
		}
		if(!checked){

			try{
				PackageInfo pkgInfo = Res.getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
                assert pkgInfo.signatures != null;
                for (Signature appSignature : pkgInfo.signatures){
					try{
						X509Certificate cert = X509Certificate.getInstance(appSignature.toByteArray());
						cert.verify(publicKey);
						verified = true;
						break;
					}
					catch (Exception e){
						verified = false;
					}
				}// for each signature
			}
			catch (NameNotFoundException nfne){
				verified = false;
			}
			catch (Throwable e){
				verified = false;
				checked = false;
			}
			finally{
				checked = true;
			}
		}
		
		return verified;
	}

    /////////////////////////////////////////////////////////////////////////////////////////////

    private PackageVerifier(){
        // nothing
    }
}
