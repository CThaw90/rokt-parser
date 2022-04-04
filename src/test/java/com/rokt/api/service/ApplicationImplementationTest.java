package com.rokt.api.service;

import com.rokt.api.dto.RequestDto;
import com.rokt.api.dto.ResponseDto;
import com.rokt.api.util.Transformer;
import com.rokt.api.util.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class ApplicationImplementationTest {

    @Test
    public void ApplicationImplementation_serveRequest_shouldReturnEmptyListIfNoParsedFiles_Test() {
        ApplicationImplementation applicationImplementation = new ApplicationImplementation(new Transformer(), new Validator());
        RequestDto requestDto = new RequestDto();
        requestDto.setFilename("sample1.txt");
        requestDto.setFrom("2000-01-01T02:38:29Z");
        requestDto.setTo("2000-06-07T23:00:00Z");
        try (MockedConstruction<File> mockedFile = Mockito.mockConstruction(File.class, (mock, context) ->
            Mockito.when(mock.listFiles()).thenReturn(null))) {
            List<ResponseDto> results = applicationImplementation.serveRequest(requestDto);
            Assertions.assertEquals(0, results.size());
        }
    }

    @Test
    public void ApplicationImplementation_serveRequest_shouldReturnEmptyListIfNoFilesMatchInput_Test() {
        File file1 = Mockito.mock(File.class);
        Mockito.when(file1.getName()).thenReturn("sample1.txt");

        File file2 = Mockito.mock(File.class);
        Mockito.when(file2.getName()).thenReturn("sample2.txt");

        File file3 = Mockito.mock(File.class);
        Mockito.when(file3.getName()).thenReturn("sample3.txt");

        File[] fileList = new File[] {file1, file2, file3};

        ApplicationImplementation applicationImplementation = new ApplicationImplementation(new Transformer(), new Validator());
        RequestDto requestDto = new RequestDto();
        requestDto.setFilename("sample4.txt");
        requestDto.setFrom("2000-01-01T02:38:29Z");
        requestDto.setTo("2000-06-07T23:00:00Z");

        try (MockedConstruction<File> mockedFile = Mockito.mockConstruction(File.class, (mock, context) ->
                Mockito.when(mock.listFiles()).thenReturn(fileList))) {
            List<ResponseDto> results = applicationImplementation.serveRequest(requestDto);
            Assertions.assertEquals(0, results.size());
        }
    }

    @Test
    public void ApplicationImplementation_serveRequest_shouldReturnEmptyListIfDateRangeInvalid_Test() {
        File file1 = Mockito.mock(File.class);
        Mockito.when(file1.getName()).thenReturn("sample1.txt");

        File file2 = Mockito.mock(File.class);
        Mockito.when(file2.getName()).thenReturn("sample2.txt");

        File file3 = Mockito.mock(File.class);
        Mockito.when(file3.getName()).thenReturn("sample3.txt");

        File[] fileList = new File[] {file1, file2, file3};

        ApplicationImplementation applicationImplementation = new ApplicationImplementation(new Transformer(), new Validator());
        RequestDto requestDto = new RequestDto();
        requestDto.setFilename("sample1.txt");
        requestDto.setFrom("2000-01-01");
        requestDto.setTo("2000-06-07T23:00:00Z");

        try (MockedConstruction<File> mockedFile = Mockito.mockConstruction(File.class, (mock, context) ->
                Mockito.when(mock.listFiles()).thenReturn(fileList))) {
            List<ResponseDto> results = applicationImplementation.serveRequest(requestDto);
            Assertions.assertEquals(0, results.size());
        }
    }

    @Test
    public void ApplicationImplementation_serveRequest_Test() {
        File file1 = Mockito.mock(File.class);
        Mockito.when(file1.getName()).thenReturn("sample1.txt");

        File file2 = Mockito.mock(File.class);
        Mockito.when(file2.getName()).thenReturn("sample2.txt");

        File file3 = Mockito.mock(File.class);
        Mockito.when(file3.getName()).thenReturn("sample3.txt");

        File[] fileList = new File[] {file1, file2, file3};

        ApplicationImplementation applicationImplementation = new ApplicationImplementation(new Transformer(), new Validator());
        RequestDto requestDto = new RequestDto();
        requestDto.setFilename("sample1.txt");
        requestDto.setFrom("2000-01-01T02:38:29Z");
        requestDto.setTo("2000-06-20T23:00:00Z");

        try (
                MockedConstruction<File> mockedFile = Mockito.mockConstruction(File.class, (mock, context) ->
                        Mockito.when(mock.listFiles()).thenReturn(fileList))
        ) {
            try (MockedConstruction<FileReader> mockedFileReader = Mockito.mockConstruction(FileReader.class)) {
                try (
                        MockedConstruction<BufferedReader> mockedBufferedReader = Mockito.mockConstruction(BufferedReader.class, (mock, context) ->
                                Mockito.when(mock.readLine())
                                .thenReturn("2000-01-12T02:04:09Z cassandre.okeefe@schuppe.ca 22964818-4fd4-4b5f-98f1-19a344fd7542")
                                .thenReturn("2000-02-12T21:09:53Z malvina_keeling@hicklekoss.com abac4d13-7948-46dc-8a68-b7a84788b91e")
                                .thenReturn("2000-03-13T02:17:48Z efren.hettinger@boyle.com 508f0ccc-3e13-4a86-8211-e774b78426ca")
                                .thenReturn("2000-04-14T05:59:19Z olga@aufderhar.biz 1f78aa23-6f25-4462-a5d6-0ebdba3373ab")
                                .thenReturn("2000-05-14T16:30:10Z cloyd@greenfelderschaden.biz 5375b2f5-bb1a-4512-8d13-0be5aa2db237")
                                .thenReturn("2000-06-15T12:06:15Z esteban@hintzmarks.name 52f646ea-196b-4ec6-8a01-9d84c12d29cd")
                                .thenReturn("2000-07-16T07:48:24Z sincere@hahnstehr.biz d39b99f0-1e13-4c6f-8323-c7459bcc7252")
                                .thenReturn("2000-08-17T03:13:22Z elyssa.kilback@gaylord.com f2586fbd-9cce-4fb5-ae87-9d0468dd4ef9")
                                .thenReturn("2000-09-18T08:32:21Z tyrique_wisozk@hyatt.name 46611362-4a16-4c75-936b-dc5d8407e7e3")
                                .thenReturn("2000-10-19T06:54:58Z loyce@carterkessler.biz 062a5a7f-f585-445d-b468-fea59b278038")
                        )
                ) {
                    List<ResponseDto> results = applicationImplementation.serveRequest(requestDto);
                    Assertions.assertEquals(6, results.size());

                    ResponseDto response0 = new ResponseDto();
                    response0.setEventTime("2000-01-12T02:04:09Z");
                    response0.setEmail("cassandre.okeefe@schuppe.ca");
                    response0.setSessionId("22964818-4fd4-4b5f-98f1-19a344fd7542");
                    Assertions.assertEquals(response0, results.get(0));

                    ResponseDto response1 = new ResponseDto();
                    response1.setEventTime("2000-02-12T21:09:53Z");
                    response1.setEmail("malvina_keeling@hicklekoss.com");
                    response1.setSessionId("abac4d13-7948-46dc-8a68-b7a84788b91e");
                    Assertions.assertEquals(response1, results.get(1));

                    ResponseDto response2 = new ResponseDto();
                    response2.setEventTime("2000-03-13T02:17:48Z");
                    response2.setEmail("efren.hettinger@boyle.com");
                    response2.setSessionId("508f0ccc-3e13-4a86-8211-e774b78426ca");
                    Assertions.assertEquals(response2, results.get(2));

                    ResponseDto response3 = new ResponseDto();
                    response3.setEventTime("2000-04-14T05:59:19Z");
                    response3.setEmail("olga@aufderhar.biz");
                    response3.setSessionId("1f78aa23-6f25-4462-a5d6-0ebdba3373ab");
                    Assertions.assertEquals(response3, results.get(3));

                    ResponseDto response4 = new ResponseDto();
                    response4.setEventTime("2000-05-14T16:30:10Z");
                    response4.setEmail("cloyd@greenfelderschaden.biz");
                    response4.setSessionId("5375b2f5-bb1a-4512-8d13-0be5aa2db237");
                    Assertions.assertEquals(response4, results.get(4));

                    ResponseDto response5 = new ResponseDto();
                    response5.setEventTime("2000-06-15T12:06:15Z");
                    response5.setEmail("esteban@hintzmarks.name");
                    response5.setSessionId("52f646ea-196b-4ec6-8a01-9d84c12d29cd");
                    Assertions.assertEquals(response5, results.get(5));
                }
            }
        }
    }
}
