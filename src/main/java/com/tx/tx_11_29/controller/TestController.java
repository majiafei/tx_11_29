package com.tx.tx_11_29.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/hello2")
    public String hello2() {
        return "element-ui";
    }
//    @RequestMapping("/download")
/*    public void download(@RequestParam(value = "ids") String ids, HttpServletResponse response) {
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            if (StringUtils.isEmpty(ids)) {
                outputStream.write("请选择需要下载的记录".getBytes());
            }
            String[] split = ids.split(",");

            List<Integer> idList = Lists.newArrayList();
            for (String id : split) {
                idList.add(Integer.valueOf(id));
            }

            SimpleDateFormat format1 = new SimpleDateFormat(
                    "yyyyMMddHHmmss");
            String filename = "广告下架管理" + format1.format(new Date())
                    + ".xls";
            filename = URLEncoder.encode(filename,"UTF-8");

            response.setHeader("Content-Disposition",
                    "attachment; filename=" + filename);
            response.setContentType("application/octet-stream; charset=utf-8");

            List<ExcelAdsOfflineVO> excelAdsOfflineVOList = sensitiveWordAdsOfflineService.download(idList);

            HSSFWorkbook hssfWorkbook = PoiUtils.exportWorkBook("广告下架管理", ExcelAdsOfflineVO.class, excelAdsOfflineVOList);

            hssfWorkbook.write(outputStream);
            outputStream.flush();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            try {
                outputStream.write(e.getMessage().getBytes());
            } catch (IOException e1) {
                logger.error(e.getMessage(), e);
            }
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error("close output stream failed", e);
                }
            }
        }
		*//*OutputStream ouputStream = null;
		try {
			SimpleDateFormat format1 = new SimpleDateFormat(
					"yyyyMMddHHmmss");
			String filename = SHEET_NAME_ACCOUNT_PERIOD + format1.format(new Date())
					+ ".xls";
			filename = URLEncoder.encode(filename,"UTF-8");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + filename);
			response.setContentType("application/octet-stream; charset=utf-8");
			ouputStream = response.getOutputStream();

			PagingBean pagingBean = new PagingBean(0, Integer.MAX_VALUE);
			List<AccountPeriodVO> accountPeriodVOList = financeService.getAccountPeriodData(financeVO, pagingBean);
			HSSFWorkbook hssfWorkbook = PoiUtils.exportWorkBook(SHEET_NAME_ACCOUNT_PERIOD, AccountPeriodVO.class, accountPeriodVOList);

			hssfWorkbook.write(ouputStream);
			ouputStream.flush();
		} catch (Exception e) {
			logger.error("exportPreparedPaymentNotArrived failed", e);
		} finally {
			try {
				if (ouputStream != null) {
					ouputStream.close();
				}
			} catch (IOException e) {
				logger.error("exportPreparedPaymentNotArrived failed", e);
			}
		}*//*
    }*/

}
