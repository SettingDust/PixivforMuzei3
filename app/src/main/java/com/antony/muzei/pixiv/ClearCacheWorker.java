/*
    This file is part of PixivforMuzei3.

    PixivforMuzei3 is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program  is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
*/

package com.antony.muzei.pixiv;

import android.content.Context;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.apache.commons.io.FileUtils;

public class ClearCacheWorker extends Worker
{
	public ClearCacheWorker(
			@NonNull Context context,
			@NonNull WorkerParameters params)
	{
		super(context, params);
	}

	@NonNull
	@Override
	public Result doWork()
	{
//        Uri conResUri = ProviderContract.getProviderClient(getApplicationContext(), PixivArtProvider.class).getContentUri();
//        getApplicationContext().getContentResolver().delete(conResUri, null, null);
		PixivArtWorker.enqueueLoad(true);
		FileUtils.deleteQuietly(getApplicationContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES));
		FileUtils.deleteQuietly(getApplicationContext().getCacheDir());
		return Result.success();
	}
}
