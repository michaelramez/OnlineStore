CREATE TABLE Product
(
  Pid Serial NOT NULL,
  Ptype Text NOT NULL,
  Pmodel Text NOT NULL,
  PQuantity INT NOT NULL,
  PPrice float NOT NULL,
  Pimage_path Text NOT NULL,
  PRIMARY KEY (Pid)
);

CREATE TABLE Customer
(
  Cid serial NOT NULL,
  Cname Text NOT NULL,
  CDoB Date NOT NULL,
  Cusername Text NOT NULL,
  Cpassword Text NOT NULL,
  CPhone Text NOT NULL,
  Cjob Text ,
  Cmail Text NOT NULL,
  Caddress Text ,
  Cinterests Text,
  Ccredit_limit float DEFAULT 0,
  PRIMARY KEY (Cid)
);

CREATE TABLE Orders
(
  Odate Date NOT NULL,
  Oid serial NOT NULL,
  OQuantity INT NOT NULL,
  Pid INT NOT NULL,
  Cid INT NOT NULL,
  PRIMARY KEY (Oid, Pid, Cid),
  FOREIGN KEY (Pid) REFERENCES Product(Pid),
  FOREIGN KEY (Cid) REFERENCES Customer(Cid)
);