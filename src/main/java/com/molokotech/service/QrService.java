package com.molokotech.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.molokotech.model.QR;
import com.molokotech.repository.QrRepository;

@Service
public class QrService {

	@Autowired
	QrRepository qrRepository;
	
	public void createQr(QR qr) {
		qrRepository.save(qr);
	}
	
	public List<QR> readAllQr(List<QR> list) {
		return list;
	}
	
	public QR findById(String id) {
		Optional<QR> opt = qrRepository.findById(id) ;
		QR qr = new QR();
		if(opt.isPresent()){	
			qr = opt.get();
		}else {
			System.out.println("Algo salio mal");
		}
		return qr;
	}
}
