1. Start a Server by executing ServerLauncher

2. Start a client by executing ClientLauncher. Grab the lock and make a change.

3. Review the messages that appear on the output to see what each application gets.

  SERVER:
  -------
  <?xml version="1.0" encoding="UTF-8"?><request id="5932aa3b-85e3-46df-8ab1-eb323509a2b8"><lockStatusRequest/></request>
  <?xml version="1.0" encoding="UTF-8"?><request id="962c7829-61d7-4940-9bc0-20ff8bfee4ac"><modelRequest/></request>
  <?xml version="1.0" encoding="UTF-8"?><request id="2c688c19-bccc-4125-8ec0-930ade4fdf67"><lockRequest grab="true"/></request>
  <?xml version="1.0" encoding="UTF-8"?><request id="40b3f4dc-9fdb-4cd6-af02-cffa906ee4ae"><updateRequest color="0" height="108" width="78"/></request>
  <?xml version="1.0" encoding="UTF-8"?><request id="1c4187cb-6d87-4634-8903-58b3b49ba3d4"><lockRequest grab="false"/></request>
  
  CLIENT:
  -------
  Received:<?xml version="1.0" encoding="UTF-8"?><response id="5e098f6e-126a-45d2-b3c8-1368be4b77bf" success="true"><connectResponse id="c3bbe6f9-e1eb-4744-82c8-27fc70e525d4"/></response>
  Received:<?xml version="1.0" encoding="UTF-8"?><response id="5932aa3b-85e3-46df-8ab1-eb323509a2b8" success="true"><lockStatusResponse secured="false"/></response>
  Received:<?xml version="1.0" encoding="UTF-8"?><response id="962c7829-61d7-4940-9bc0-20ff8bfee4ac" success="true"><modelResponse color="0" height="10" width="10"/></response>
  Received:<?xml version="1.0" encoding="UTF-8"?><response id="2c688c19-bccc-4125-8ec0-930ade4fdf67" success="true"><lockResponse secured="true"/></response>
  Received:<?xml version="1.0" encoding="UTF-8"?><response id="40b3f4dc-9fdb-4cd6-af02-cffa906ee4ae" success="true"><modelResponse color="0" height="108" width="78"/></response>
  Received:<?xml version="1.0" encoding="UTF-8"?><response id="40b3f4dc-9fdb-4cd6-af02-cffa906ee4ae" success="true"><modelResponse color="0" height="108" width="78"/></response>
  Received:<?xml version="1.0" encoding="UTF-8"?><response id="1c4187cb-6d87-4634-8903-58b3b49ba3d4" success="true"><lockStatusResponse secured="false"/></response>
  
These customized client/server applications work because of the XML protocol designed and described
in distributedEBC.xsd

