package com.es.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.es.model.Berkas;
import com.es.repository.BerkasRepository;

@Controller
@RequestMapping("")
public class BerkasController {

	@Value("${com.es.path}")
	private String path;

	@Autowired
	private BerkasRepository berkasRepository;

	@ModelAttribute("entity")
	public Berkas getBerkas() {
		return new Berkas();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("entity") Berkas berkas, @RequestParam("file") MultipartFile file, Model model) {

		if (file.isEmpty()) {
			model.addAttribute("pesan", "Gambar belum dipilih");
			return "index";
		}
		try {
			if (file != null) {
				try {
					String extenstion = FilenameUtils.getExtension(file.getOriginalFilename());
					String fileName = file.getOriginalFilename() + "." + extenstion;

					Date tglUpload = new Date();
					// <file disimpan di database
					berkas.setNamaBerkas(fileName);
					berkas.setExtension(extenstion);
					berkas.setTglUpload(tglUpload);
					byte[] bytes = file.getBytes();
					berkas.setByteBerkas(bytes);
					// file disimpan di database>

					// <file disimpan di folder
					BufferedOutputStream buffStream = new BufferedOutputStream(
							new FileOutputStream(new File(path + fileName)));
					buffStream.write(bytes);
					buffStream.close();
					// file disimpan di folder>
				} catch (Exception e) {
					model.addAttribute("pesan", "Error Upload Foto");
				}

			}

			berkasRepository.save(berkas);
			model.addAttribute("pesan", "Data berhasil disimpan");

		} catch (Exception e) {
			model.addAttribute("pesan", "Data  Error Disimpan");

		}

		berkasRepository.save(berkas);
		model.addAttribute("pesan", "Data berhasil disimpan");
		return "index";
	}
}
