<?php

// Import the Apache HTTP client from HTTP Components (http://hc.apache.org/httpcomponents-client-ga/)
require_once 'HTTP/Request2.php';
$subkey = 'insert azure api key here';

//Variables
$sourceImageUrl = $_GET['s'];

//Programm
$faceID = getFaceID($sourceImageUrl);
$personID = getPersonID($faceID);
$personName = getPersonName($personID);

//return the value
echo json_encode($personName);

//Function to detect Face for a Image; Returns FaceID
function getFaceID($sourceImageUrl) {
    $request = new Http_Request2('https://digibp-face.cognitiveservices.azure.com/face/v1.0/detect');
    $url = $request->getUrl();

    $headers = array(
    // Request headers
        'Content-Type' => 'application/json',
        'Ocp-Apim-Subscription-Key' => $subkey,
    );

    $request->setHeader($headers);

    $parameters = array(
    // Request parameters
        'returnFaceId' => 'true',
        'returnFaceLandmarks' => 'false',
        'recognitionModel' => 'recognition_02'
    );

    $url->setQueryVariables($parameters);

    $request->setMethod(HTTP_Request2::METHOD_POST);

    // Request body
    $request->setBody('{"url": "'.$sourceImageUrl.'"}');

    try
    {
        $response = $request->send();
        $return = json_decode($response->getBody());
        return $return[0]->faceId;
    }
    catch (HttpException $ex)
    {
        return $ex;
    }
}

//Function which takes a FaceID and returns a PersonID if face was found in given group
function getPersonID($faceID) {
    $request = new Http_Request2('https://digibp-face.cognitiveservices.azure.com/face/v1.0/identify');
    $url = $request->getUrl();

    $headers = array(
    // Request headers
        'Content-Type' => 'application/json',
        'Ocp-Apim-Subscription-Key' => $subkey,
    );

    $request->setHeader($headers);

    $parameters = array(
    // Request parameters
    );
    
    $url->setQueryVariables($parameters);

    $request->setMethod(HTTP_Request2::METHOD_POST);

    // Request body
    $var = '{
        "personGroupId": "paneer",
        "faceIds": [
            "'.$faceID.'"
        ],
        "maxNumOfCandidatesReturned": 1,
        "confidenceThreshold": 0.5
    }';
    $request->setBody($var);


    try
    {
        $response = $request->send();
        $return = json_decode($response->getBody());
       if(count($return)>0) {
           $candidates = $return[0]->candidates;
           if(count($candidates)>0) {
               return $candidates[0]->personId;
           }
       }
    
    }
    catch (HttpException $ex)
    {
        return $ex;
    }
}

//Takes PersonID and returns Persons Name
 function getPersonName($personID) {

    $request = new Http_Request2('https://digibp-face.cognitiveservices.azure.com/face/v1.0/persongroups/paneer/persons/'.$personID);
    $url = $request->getUrl();

    $headers = array(
    // Request headers
        'Ocp-Apim-Subscription-Key' => $subkey,
    );

    $request->setHeader($headers);

    $parameters = array(
    // Request parameters
        'returnRecognitionModel' => 'false',
    );

    $url->setQueryVariables($parameters);

    $request->setMethod(HTTP_Request2::METHOD_GET);

    // Request body
    $request->setBody("{body}");

    try
    {
        $response = $request->send();
        $return = json_decode($response->getBody());
        if(count($return)>0) {        
            return $return->name;
        }
        return $return;
    }
    catch (HttpException $ex)
    {
        return $ex;
    }
 }
?>
