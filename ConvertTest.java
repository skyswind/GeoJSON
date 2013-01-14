package com.supermap.services.util;

import org.json.JSONException;

import com.supermap.data.CursorType;
import com.supermap.data.DatasetVector;
import com.supermap.data.GeoRegion;
import com.supermap.data.Recordset;
import com.supermap.data.Workspace;
import com.supermap.data.WorkspaceConnectionInfo;
import com.supermap.services.components.commontypes.Geometry;
import com.supermap.services.rest.util.JsonConverter;

public class ConvertTest {
	public static void main(String[] argus) throws JSONException {
		Workspace workspace = new Workspace();
		WorkspaceConnectionInfo conn = new WorkspaceConnectionInfo("E:/工作/product/testdata/test.sxwu");
		if (!workspace.open(conn)) {
			System.out.println("打开工作空间失败");
			return;
		}
		// 面数据集
		DatasetVector datasetVector = (DatasetVector) workspace.getDatasources().get(0).getDatasets().get("Geomor_R");
		Recordset recordset = datasetVector.query(new int[]{1, 2}, CursorType.STATIC);
		GeoRegion region = (GeoRegion) recordset.getGeometry();

		com.supermap.services.providers.util.UGOConvertTool convertTool = new com.supermap.services.providers.util.UGOConvertTool();
		// 将ugo的对象转化为json字符串
		String resultStr = convertTool.toJson(region);
		System.out.println(resultStr);

		JsonConverter jsonConvert = new JsonConverter();
		com.supermap.services.components.commontypes.Geometry iserverGeo = (Geometry) jsonConvert.toObject(resultStr,
				com.supermap.services.components.commontypes.Geometry.class);
	}
}
