package xyz.lomasz.springhelloworld.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import xyz.lomasz.springhelloworld.model.Timestamp;

@RestController
@Api(value = "Timestamp", description = "Ekki-Ekki-Ekki-Ekki-PTANG. Zoom-Boing. Z'nourrwringmm")
public class TimestampController {

    @ApiOperation(value = "What time is it, dude?", response = Timestamp.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Niceeee!"),
            @ApiResponse(code = 401, message = "Not nice!"),
            @ApiResponse(code = 403, message = "U R not awesome enough, go away!"),
            @ApiResponse(code = 404, message = "Wrong way, dude!"),
            @ApiResponse(code = 666, message = "Highway to Hell!")
    }
    )
    @RequestMapping(value = "getDateTime", method = RequestMethod.GET)
    Timestamp getDateTime() {
        return new Timestamp();
    }
}